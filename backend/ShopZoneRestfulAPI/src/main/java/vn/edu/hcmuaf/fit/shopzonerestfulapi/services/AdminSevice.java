package vn.edu.hcmuaf.fit.shopzonerestfulapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.RoleRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AdminSevice {
    private AdminRepository adminRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ApiResponse<String> createAdmin(RegisterRequest registerRequest) {
        if (adminRepository.existsByUsername(registerRequest.getUsername())) {
            return ApiResponse.<String>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }

        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(registerRequest.getUsername());
        adminEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        adminEntity.setFullName(registerRequest.getFullName());
        adminEntity.setEmail(registerRequest.getEmail());
        adminEntity.setPhone(registerRequest.getPhone());

        Role role = roleRepository.findByName("ADMIN");
        adminEntity.setRoles(Collections.singleton(role));
        adminRepository.save(adminEntity);

        return ApiResponse.<String>builder()
                .code(200)
                .message("Admin registered successfully!")
                .build();
    }
}
