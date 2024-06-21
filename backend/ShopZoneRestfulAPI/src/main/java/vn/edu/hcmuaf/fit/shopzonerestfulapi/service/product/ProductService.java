package vn.edu.hcmuaf.fit.shopzonerestfulapi.service.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.product.ProductRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.product.ReviewRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Category;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Product;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Review;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Variation;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.product.ProductRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.product.ReviewRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final Cloudinary cloudinary;

    public ApiResponse<List<Product>> searchProductByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return ApiResponse.<List<Product>>builder()
                .code(200)
                .message("Search product by name successfully!")
                .result(products)
                .build();
    }

    public ApiResponse<Product> createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDiscount(productRequest.getDiscount());
        product.setOfferEnd(productRequest.getOfferEnd());
        product.setNew(productRequest.isNew());
        product.setRating(productRequest.getRating());
        product.setSaleCount(productRequest.getSaleCount());
        product.setCategories(productRequest.getCategoryIds().stream().map(categoryId -> {
            Category category = new Category();
            category.setId(categoryId);
            return category;
        }).collect(Collectors.toSet()));
        product.setVariations(productRequest.getVariationIds().stream().map(variationId -> {
            Variation variation = new Variation();
            variation.setId(variationId);
            return variation;
        }).collect(Collectors.toList()));
        product.setImages(productRequest.getImages().stream().map(image -> {
            try {
                Map uploadResult = cloudinary.uploader().upload(
                        image.getBytes(),
                        ObjectUtils.asMap("folder", "shopzone/product/" + product.getName())
                );
                return (String) uploadResult.get("url");
            } catch (IOException e) {
                throw new RuntimeException("Error uploading image to Cloudinary", e);
            }
        }).collect(Collectors.toList()));
        product.setShortDescription(productRequest.getShortDescription());
        product.setFullDescription(productRequest.getFullDescription());

        return ApiResponse.<Product>builder()
                .code(200)
                .message("Create product successfully!")
                .result(productRepository.save(product))
                .build();
    }

    public ApiResponse<Product> updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ApiResponse.<Product>builder()
                    .code(404)
                    .message("Product not found!")
                    .build();
        }
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDiscount(productRequest.getDiscount());
        product.setOfferEnd(productRequest.getOfferEnd());
        product.setNew(productRequest.isNew());
        product.setRating(productRequest.getRating());
        product.setSaleCount(productRequest.getSaleCount());
        product.setCategories(productRequest.getCategoryIds().stream().map(categoryId -> {
            Category category = new Category();
            category.setId(categoryId);
            return category;
        }).collect(Collectors.toSet()));
        product.setVariations(productRequest.getVariationIds().stream().map(variationId -> {
            Variation variation = new Variation();
            variation.setId(variationId);
            return variation;
        }).collect(Collectors.toList()));
        product.setImages(productRequest.getImages().stream().map(image -> {
            try {
                Map uploadResult = cloudinary.uploader().upload(
                        image.getBytes(),
                        ObjectUtils.asMap("folder", "shopzone/product/" + product.getName())
                );
                return (String) uploadResult.get("url");
            } catch (IOException e) {
                throw new RuntimeException("Error uploading image to Cloudinary", e);
            }
        }).collect(Collectors.toList()));
        product.setShortDescription(productRequest.getShortDescription());
        product.setFullDescription(productRequest.getFullDescription());

        return ApiResponse.<Product>builder()
                .code(200)
                .message("Update product successfully!")
                .result(productRepository.save(product))
                .build();
    }

    public ApiResponse<Product> deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return ApiResponse.<Product>builder()
                .code(200)
                .message("Delete product successfully!")
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
                .message("Get product by id successfully!")
                .result(product)
                .build();
    }

    public ApiResponse<List<Product>> getAllProducts() {
        return ApiResponse.<List<Product>>builder()
                .code(200)
                .message("Get all products successfully!")
                .result(productRepository.findAll())
                .build();
    }

    public ApiResponse<Product> addReview(Authentication authentication, Long productId, ReviewRequest reviewRequest) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<Product>builder()
                    .code(401)
                    .message("Unauthorized!")
                    .build();
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return ApiResponse.<Product>builder()
                    .code(404)
                    .message("Product not found!")
                    .build();
        }
        Review review = new Review();
        review.setUsername(username);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setReviewDate(LocalDate.now());
        review.setProduct(product);
        reviewRepository.save(review);

        product.getReviews().add(review);
        product.updateRating();

        return ApiResponse.<Product>builder()
                .code(200)
                .message("Add review successfully!")
                .result(productRepository.save(product))
                .build();
    }
}
