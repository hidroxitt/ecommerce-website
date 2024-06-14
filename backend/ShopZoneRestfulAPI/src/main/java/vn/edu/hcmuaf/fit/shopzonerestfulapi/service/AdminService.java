package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<AdminEntity> createAdmin(SignupRequest signupRequest) {
        // Kiểm tra xem username đã tồn tại trong userRepository chưa
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ApiResponse.<AdminEntity>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }

        // Kiểm tra xem username đã tồn tại trong adminRepository chưa
        if (adminRepository.existsByUsername(signupRequest.getUsername())) {
            return ApiResponse.<AdminEntity>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }

        // Tạo mới một AdminEntity
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(signupRequest.getUsername());
        adminEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        adminEntity.setFullName(signupRequest.getFullName());
        adminEntity.setEmail(signupRequest.getEmail());
        adminEntity.setPhone(signupRequest.getPhone());

        // Lấy role ADMIN từ roleRepository
        Role role = roleRepository.findByName("ADMIN");
        adminEntity.setRole(Collections.singleton(role));
        // Lưu adminEntity vào adminRepository
        adminEntity = adminRepository.save(adminEntity);

        return ApiResponse.<AdminEntity>builder()
                .code(200)
                .message("Admin registered successfully!")
                .result(adminEntity)
                .build();
    }

    public AdminEntity updateAdmin() {
        return null;
    }

    public  AdminEntity changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    public AdminEntity getAllAdmin() {
        return null;
    }

}
