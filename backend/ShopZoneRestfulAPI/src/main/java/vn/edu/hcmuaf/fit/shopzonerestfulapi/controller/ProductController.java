package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ProductRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ReviewRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ProductResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Product;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/search")
    public ApiResponse<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        return productService.searchProductByName(keyword);
    }

    @PostMapping("/create")
    public ApiResponse<Product> createProduct(@ModelAttribute ProductRequest productRequest,
                                              @RequestParam("images") MultipartFile[] images) {
        return productService.createProduct(productRequest, List.of(images));
    }

    @PostMapping("/update/{productId}")
    public ApiResponse<Product> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest, @RequestParam("images") MultipartFile[] images){
        return productService.updateProduct(productId, productRequest, List.of(images));
    }

    @PostMapping("/delete")
    public ApiResponse<String> deleteProduct(Long productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/get/{productId}")
    public ApiResponse<Product> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/get/all")
    public ApiResponse<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add-review/{productId}")
    public ApiResponse<ProductResponse> addReviewToProduct(Authentication authentication, @PathVariable Long productId, @RequestBody ReviewRequest reviewRequest) {
        return productService.addReviewToProduct(authentication, productId, reviewRequest);
    }

}
