package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpdateSellerRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.SellerRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SellerEntity createSeller(SignupRequest signupRequest) {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setUsername(signupRequest.getUsername());
        sellerEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        sellerEntity.setFullName(signupRequest.getFullName());
        sellerEntity.setEmail(signupRequest.getEmail());
        sellerEntity.setPhone(signupRequest.getPhone());

        Role role = roleRepository.findByName("SELLER");
        sellerEntity.setRole(Collections.singleton(role));

        return sellerRepository.save(sellerEntity);
    }

    public SellerEntity updateSeller(Long sellerId, UpdateSellerRequest updateSellerRequest) {
        SellerEntity currentSeller = (SellerEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentSellerId = currentSeller.getId();

        if (!currentSellerId.equals(sellerId)) {
            throw new RuntimeException("You are not allowed to update this seller");
        } else {
            return sellerRepository.findById(sellerId).map(
                    seller -> {
                        seller.setFullName(updateSellerRequest.getFullName());
                        seller.setDob(updateSellerRequest.getDob());
                        seller.setEmail(updateSellerRequest.getEmail());
                        seller.setPhone(updateSellerRequest.getPhone());
                        seller.setAddress(updateSellerRequest.getAddress());
                        return sellerRepository.save(seller);
                    }
            ).orElseThrow(() -> new RuntimeException("Seller not found with id " + sellerId));
        }
    }

    public SellerEntity deleteSeller(Long sellerId) {
        SellerEntity currentSeller = (SellerEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentSellerId = currentSeller.getId();
        if (!currentSellerId.equals(sellerId)) {
            throw new RuntimeException("You are not allowed to delete this seller");
        } else {
            return sellerRepository.findById(sellerId).map(
                    seller -> {
                        sellerRepository.delete(seller);
                        return seller;
                    }
            ).orElseThrow(() -> new RuntimeException("Seller not found"));
        }

    }

    public SellerEntity changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    public SellerEntity getAllSeller() {
        return null;
    }
}
