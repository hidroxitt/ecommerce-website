package vn.edu.hcmuaf.fit.shopzonerestfulapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.SellerRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class SellerService {
    private SellerRepository sellerRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public ApiResponse<String> createSeller(RegisterRequest registerRequest) {
        if (sellerRepository.existsByUsername(registerRequest.getUsername())) {
            return ApiResponse.<String>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setUsername(registerRequest.getUsername());
        sellerEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        sellerEntity.setFullName(registerRequest.getFullName());
        sellerEntity.setEmail(registerRequest.getEmail());
        sellerEntity.setPhone(registerRequest.getPhone());

        Role role = roleRepository.findByName("SELLER");
        sellerEntity.setRoles(Collections.singleton(role));
        sellerRepository.save(sellerEntity);

        return ApiResponse.<String>builder()
                .code(200)
                .message("Seller registered successfully!")
                .build();
    }
}
