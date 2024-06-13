package vn.edu.hcmuaf.fit.shopzonerestfulapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpdateSellerRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.SellerService;

@RestController
@RequestMapping("/seller")
@AllArgsConstructor
public class SellerController {

    private SellerService sellerService;

    @PostMapping("/register")
    public ApiResponse<String> registerSeller(@RequestBody RegisterRequest registerRequest) {
        return sellerService.createSeller(registerRequest);
    }

    @PutMapping("/update/{sellerId}")
    public ApiResponse<String> updateSeller(@PathVariable Long sellerId, @RequestBody UpdateSellerRequest updateSellerRequest) {
        return sellerService.updateSeller(sellerId, updateSellerRequest);
    }
}
