package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;

import java.util.List;

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
        adminEntity.setRole(role);
        // Lưu adminEntity vào adminRepository
        adminRepository.save(adminEntity);

        return ApiResponse.<AdminEntity>builder()
                .code(200)
                .message("Admin registered successfully!")
                .result(adminEntity)
                .build();
    }

    public AdminEntity updateAdmin() {
        return null;
    }

    public  ApiResponse<AdminEntity> changePassword(Authentication authentication, ChangePasswordRequest changePasswordRequest) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<AdminEntity>builder()
                    .code(400)
                    .message("You are not logged in!")
                    .build();
        } else {
            AdminEntity admin = adminRepository.findByUsername(username);
            if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), admin.getPassword())) {
                return ApiResponse.<AdminEntity>builder()
                        .code(400)
                        .message("Old password is incorrect!")
                        .build();
            } else {
                admin.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                adminRepository.save(admin);
                return ApiResponse.<AdminEntity>builder()
                        .code(200)
                        .message("Change password successfully!")
                        .result(admin)
                        .build();
            }
        }
    }

    public ApiResponse<List<UserEntity>> getAllUsers(Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<List<UserEntity>>builder()
                    .code(400)
                    .message("You are not logged in!")
                    .build();
        } else {
            return ApiResponse.<List<UserEntity>>builder()
                    .code(200)
                    .message("Get all user information successfully!")
                    .result(userRepository.findAll())
                    .build();
        }
    }
}
