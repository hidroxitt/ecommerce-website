package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpdateSellerRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.SellerService;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PutMapping("/update/{sellerId}")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<SellerEntity> update(@PathVariable Long sellerId, @RequestBody UpdateSellerRequest updateSellerRequest) {
            return ResponseEntity.ok(sellerService.updateSeller(sellerId, updateSellerRequest));
    }

    @DeleteMapping("/delete/{sellerId}")
    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    public ResponseEntity<SellerEntity> delete(@PathVariable Long sellerId) {
        return ResponseEntity.ok(sellerService.deleteSeller(sellerId));
    }
}
