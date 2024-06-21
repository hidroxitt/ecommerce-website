package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.product.ProductRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.product.ReviewRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Product;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/search")
    public ApiResponse<List<Product>> searchProductByName(String name) {
        return productService.searchProductByName(name);
    }

    @PostMapping("/create")
    public ApiResponse<Product> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PostMapping("/update")
    public ApiResponse<Product> updateProduct(Long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @PostMapping("/delete")
    public ApiResponse<Product> deleteProduct(Long productId) {
        return productService.deleteProduct(productId);
    }

    @PostMapping("/get")
    public ApiResponse<Product> getProduct(Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/get-all")
    public ApiResponse<List<Product>> getAllProduct() {
        return productService.getAllProducts();
    }

    @PostMapping("/add-review")
    public ApiResponse<Product> addReview(Authentication authentication, Long productId, ReviewRequest reviewRequest) {
        return productService.addReview(authentication, productId, reviewRequest);
    }
}
