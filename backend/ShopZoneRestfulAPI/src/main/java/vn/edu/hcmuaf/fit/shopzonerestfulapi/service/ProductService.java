package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ProductRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ReviewRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SizeRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.VariationRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ProductResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.CategoryRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.ProductRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.ReviewRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    public final Cloudinary cloudinary;

    public ApiResponse<List<Product>> searchProductByName(String keyword) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);
        return ApiResponse.<List<Product>>builder()
                .code(200)
                .message("Search product by name successfully!")
                .result(products)
                .build();
    }

    public ApiResponse<Product> createProduct(ProductRequest productRequest, List<MultipartFile> images) {
        Product product = new Product();
        productField(product, productRequest, images);
        return ApiResponse.<Product>builder()
                .code(200)
                .message("Success")
                .result(productRepository.save(product))
                .build();
    }

    public ApiResponse<Product> updateProduct(Long productId, ProductRequest productRequest, List<MultipartFile> images) {
        Product existingProduct = productRepository.findById(productId).orElse(null);
        if (existingProduct == null) {
            return ApiResponse.<Product>builder()
                    .code(404)
                    .message("Product not found!")
                    .build();
        }
        productField(existingProduct, productRequest, images);
        return ApiResponse.<Product>builder()
                .code(200)
                .message("Success")
                .result(productRepository.save(existingProduct))
                .build();
    }

    public ApiResponse<String> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found!"));
        productRepository.deleteById(productId);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Success")
                .result("Product deleted successfully!")
                .build();
    }

    public ApiResponse<Product> getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ApiResponse.<Product>builder()
                    .code(404)
                    .message("Product not found!")
                    .build();
        }
        return ApiResponse.<Product>builder()
                .code(200)
                .message("Success")
                .result(product)
                .build();
    }

    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ApiResponse.<List<Product>>builder()
                .code(200)
                .message("Success")
                .result(products)
                .build();
    }

    public ApiResponse<ProductResponse> addReviewToProduct(Authentication authentication, long productId, ReviewRequest reviewRequest) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<ProductResponse>builder()
                    .code(401)
                    .message("Unauthorized!")
                    .build();
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ApiResponse.<ProductResponse>builder()
                    .code(404)
                    .message("Product not found!")
                    .build();
        }
        Review review = new Review();
        review.setUsername(username);
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setProduct(product);
        reviewRepository.save(review);

        product.getReviews().add(review);
        product.updateRating();
        productRepository.save(product);

        ProductResponse productResponse = new ProductResponse(product);

        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Success")
                .result(productResponse)
                .build();
    }

    public void productField(Product product, ProductRequest productRequest, List<MultipartFile> images) {
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDiscount(productRequest.getDiscount());
        product.setOfferEnd(productRequest.getOfferEnd());
        product.setNew(productRequest.isNew());
        product.setRating(productRequest.getRating());
        product.setSaleCount(productRequest.getSaleCount());

        List<Category> categories = categoryRepository.findAllById(productRequest.getCategoryIds());
        product.setCategories(categories);

        List<Variation> variations = productRequest.getVariations().stream().map(this::mapToVariation).collect(Collectors.toList());
        variations.forEach(variation -> variation.setProduct(product));
        product.setVariations(variations);

        List<String> imageUrls = images.stream()
                .map(this::uploadImageToCloudinary)
                .toList();
        product.setImages(imageUrls);

        product.setShortDescription(productRequest.getShortDescription());
        product.setFullDescription(productRequest.getFullDescription());

    }

    private Variation mapToVariation(VariationRequest variationRequest) {
        Variation variation = new Variation();
        variation.setColor(variationRequest.getColor());
        variation.setImage(variationRequest.getImage());
        List<Size> sizes = variationRequest.getSizes().stream()
                .map(this::mapToSize)
                .collect(Collectors.toList());
        variation.setSizes(sizes);
        return variation;
    }

    private Size mapToSize(SizeRequest sizeRequest) {
        Size size = new Size();
        size.setName(sizeRequest.getName());
        size.setStock(sizeRequest.getStock());
        return size;
    }

    private String uploadImageToCloudinary(MultipartFile image) {
        try {
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(),
                    ObjectUtils.asMap("folder", "shopzone/product"));
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image to Cloudinary", e);
        }
    }

}
