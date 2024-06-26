package com.smarttech.service;

import com.smarttech.constant.ImageType;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.product.*;
import com.smarttech.dto.user.UserResponse;
import com.smarttech.entity.*;
import com.smarttech.exception.ValidationException;
import com.smarttech.repository.*;
import com.smarttech.repository.custom.CustomProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired, onParam_ = @Lazy)
public class ProductService {

    private final CustomProductRepository customProductRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SpecificationsRepository specificationsRepository;
    private final ImageRepository imageRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final ColorProductRepository colorProductRepository;
    private final ProductDetailRepository productDetailRepository;

    public PageResponse<ProductResponse> paginateProductList(ProductSearchRequest request) {
        return this.customProductRepository.searchProduct(request);
    }

    public List<ProductImageDTO> getProductImage(int limit) {
        return this.customProductRepository.getProductImage(limit);
    }

    public List<ProductResponse> getBestSeller(int limit) {
        return this.customProductRepository.getBestSeller(limit);
    }

    public List<ProductResponse> getNewest(int limit) {
        return this.customProductRepository.getNewest(limit);
    }

    public List<ProductResponse> getHotSales(int limit) {
        return this.customProductRepository.getHotSales(limit);
    }

    public ProductDetailResponse getDetailProduct(String code, int limitRelatedProduct) {
        Product product = this.productRepository.findByCode(code)
                .orElseThrow(() -> new ValidationException("Không tìm thấy: " + code));
        if (!product.getActive()) {
            throw new ValidationException("Không tìm thấy: " + code);
        }
        Category category = this.categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new ValidationException(""));
        if (!category.getActive()) {
            throw new ValidationException("Không tìm thấy: " + code);
        }
        String imageUrl = this.imageRepository.findByTypeAndObjectId(ImageType.PRODUCT, String.valueOf(product.getId()))
                .map(Image::getUrl)
                .orElse(StringUtils.EMPTY);

        Map<ColorProductDTO, List<ProductDetailRequest>> sizeAndColorProduct = this.customProductRepository.getSizeAndColorProduct(product.getId());
        List<ProductResponse> byCategoryId = this.customProductRepository.getByCategoryId(limitRelatedProduct, Arrays.asList(product.getCategoryId()));
        Set<String> sizes = sizeAndColorProduct.keySet()
                .stream()
                .map(sizeAndColorProduct::get)
                .flatMap(Collection::stream)
                .map(ProductDetailRequest::getSize)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<Specifications> specifications = this.specificationsRepository.findByProductId(product.getId());
        return ProductDetailResponse.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .image(imageUrl)
                .shortDescription(product.getShortDescription())
                .categoryName(category.getName())
                .categoryId(category.getId())
                .description(product.getDescription())
                .relatedProducts(byCategoryId)
                .details(sizeAndColorProduct)
                .sizesSet(sizes)
                .specifications(specifications)
                .build();
    }

    @Transactional
    public void createProduct(CreateProductRequest request) {
        if (this.productRepository.findByCode(request.getCode()).isPresent()) {
            throw new ValidationException(request.getCode() + " đã tồn tại");
        }
        UserResponse userResponse = this.userService.getCurrentUserThrowIfNot();
        Product product = new Product();
        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setCategoryId(request.getCategoryId());
        product.setDescription(request.getDescription());
        product.setCreatedBy(userResponse.getEmail());
        product.setActive(false);
        product.setIsShowHome(request.getIsShowHome());
        product.setShortDescription(request.getShortDescription());
        product = this.productRepository.save(product);
        String fileName = "";
        if (Objects.nonNull(request.getImage())) {
            fileName = product.getCode() + "-" + request.getImage().getName();
        }
        this.imageService.insertNotCommit(request.getImage(), fileName, ImageType.PRODUCT, product.getId().toString());

        Map<String, ColorProduct> colorProductMap = new HashMap<>();
        for (ColorProductDTO colorProductDTO : request.getColorProductDTOList()) {
            ColorProduct colorProduct = this.insertColorProduct(product.getId(), colorProductDTO.getColor());
            fileName = "";
            if (Objects.nonNull(colorProductDTO.getImage())) {
                fileName = product.getCode() + "-" + colorProductDTO.getImage().getName();
            }
            this.imageService.insertNotCommit(colorProductDTO.getImage(), fileName, ImageType.PRODUCT_COLOR, colorProduct.getId().toString());
            colorProductMap.put(colorProductDTO.getColor(), colorProduct);
        }

        this.insertProductDetail(request.getProductDetails(), colorProductMap, product.getId(), new HashMap<>());
        this.addSpecifications(request.getSpecifications(), product.getId());
    }

    private ColorProduct insertColorProduct(long productId, String color) {
        ColorProduct colorProduct = new ColorProduct();
        colorProduct.setProductId(productId);
        colorProduct.setName(color);
        return this.colorProductRepository.save(colorProduct);
    }

    private void insertProductDetail(Map<String, List<ProductDetailRequest>> request, Map<String, ColorProduct> colorProductMap, long productId, Map<String, ProductDetail> detailsMapInDB) {
        request.forEach((color, details) -> {
            ColorProduct colorProduct = colorProductMap.get(color);

            details.forEach(pd -> {
                ProductDetail pdInDb = detailsMapInDB.get(color + "-" + pd.getSize());
                ProductDetail productDetail = Optional.ofNullable(pdInDb).orElseGet(ProductDetail::new);
                productDetail.setColorProductId(colorProduct.getId());
                productDetail.setProductId(productId);
                productDetail.setPrice(pd.getPrice());
                productDetail.setCost(pd.getCost());
                productDetail.setPercentDiscount(pd.getDiscount());
                productDetail.setSize(pd.getSize());
                productDetail.setQuantity(pd.getQuantity());
                productDetail.setActive(true);
                productDetailRepository.save(productDetail);
            });
        });
    }

    public void lockOrUnlockProduct(String code) {
        Product product = this.productRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException(code + " không tìm thấy"));
        product.setActive( !product.getActive() );
        this.productDetailRepository.findByProductId(product.getId())
                .forEach(pd -> {
                    pd.setActive( product.getActive() );
                    this.productDetailRepository.save(pd);
                });
        this.productRepository.save(product);
    }

    @Transactional
    public void updateProduct(CreateProductRequest request) {
        Optional<Product> productOpt = this.productRepository.findByCode(request.getCode());
        if (productOpt.isPresent() && productOpt.get().getId() != request.getId()) {
            throw new ValidationException(request.getCode() + " đã tồn tại");
        }

        // update product info
        Product productInDb = productOpt.get();
        productInDb.setName(request.getName());
        productInDb.setCategoryId(request.getCategoryId());
        productInDb.setDescription(request.getDescription());
        productInDb.setIsShowHome(request.getIsShowHome());
        productInDb.setShortDescription(request.getShortDescription());
        this.productRepository.save(productInDb);

        // handle product's avatar
        if (Objects.nonNull(request.getImage()) && request.getImage().getSize() > 0) {
            String fileName = productInDb.getCode() + "-" + request.getImage().getName();
            this.imageService.insertOrUpdate(request.getImage(), fileName, ImageType.PRODUCT, String.valueOf(productInDb.getId()));
        }

        Map<String, ColorProduct> colorProductMap = new HashMap<>();

        // handle product's colors
        List<String> colors = request.getColorProductDTOList().stream()
                .map(ColorProductDTO::getColor)
                .collect(Collectors.toList());
        // remove product's color
        this.colorProductRepository.deleteColorByProductIdAndNotIn(productInDb.getId(), colors);
        Map<String, ColorProduct> colorProductDbMap = this.colorProductRepository.findByProductIdAndColors(productInDb.getId(), colors)
                .stream()
                .collect(Collectors.toMap(ColorProduct::getName, t -> t, (v1, v2) -> v2));
        Map<Long, String> colorProductNameMap = new HashMap<>();
        for (ColorProductDTO colorProductDTO : request.getColorProductDTOList()) {
            ColorProduct colorProduct = colorProductDbMap.get(colorProductDTO.getColor());

            // if product's color does not exist in DB then create new
            if (Objects.isNull(colorProduct)) {
                colorProduct = this.insertColorProduct(productInDb.getId(), colorProductDTO.getColor());
            }

            // if image is passed then upload to firebase and insert a record to db
            if (Objects.nonNull(colorProductDTO.getImage()) && colorProductDTO.getImage().getSize() > 0) {
                String fileName = productInDb.getCode() + "-" + colorProductDTO.getImage().getName();
                this.imageService.insertOrUpdate(colorProductDTO.getImage(), fileName, ImageType.PRODUCT_COLOR, colorProduct.getId().toString());
            }
            colorProductMap.put(colorProductDTO.getColor(), colorProduct);
            colorProductNameMap.put(colorProduct.getId(), colorProduct.getName());
        }

        // handle product's sizes
        // remove all product's size with product-id
        Map<String, ProductDetail> detailsMap = this.productDetailRepository.findByProductId(productInDb.getId())
                .stream()
                .collect(Collectors.toMap(p -> colorProductNameMap.get(p.getColorProductId()) + "-" + p.getSize(), p -> p));

        Map<String, ProductDetailRequest> detailMapInRequest = request.getProductDetails().values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(x -> x.getColor().getColor() + "-" + x.getSize(), x -> x));

        List<Long> removeIds = detailsMap.keySet()
                .stream()
                .filter(key -> Objects.isNull(detailMapInRequest.get(key)))
                .map(detailsMap::get)
                .map(ProductDetail::getId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(removeIds)) {
            this.productDetailRepository.deleteByIds(removeIds);
        }

        // re-create new product's size
        this.insertProductDetail(request.getProductDetails(), colorProductMap, productInDb.getId(), detailsMap);
        this.specificationsRepository.deleteByProductId(productInDb.getId());
        this.addSpecifications(request.getSpecifications(), productInDb.getId());
    }

    private void addSpecifications(List<Specifications> specifications, Long productId) {
        if (Objects.nonNull(specifications)) {
            specifications.forEach(x -> x.setProductId(productId));
            this.specificationsRepository.saveAll(specifications);
        }
    }
}
