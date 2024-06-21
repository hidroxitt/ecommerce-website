package vn.edu.hcmuaf.fit.shopzonerestfulapi.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<AdminEntity> createAdmin(SignupRequest signupRequest) {
        // Kiểm tra xem username đã tồn tại chưa
        if (userRepository.existsByUsername(signupRequest.getUsername()) || adminRepository.existsByUsername(signupRequest.getUsername())) {
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
        Role role = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        adminEntity.setRole(role);

        return ApiResponse.<AdminEntity>builder()
                .code(200)
                .message("Admin registered successfully!")
                .result(adminRepository.save(adminEntity))
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
            AdminEntity admin = adminRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: Admin is not found."));
            if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), admin.getPassword())) {
                return ApiResponse.<AdminEntity>builder()
                        .code(400)
                        .message("Old password is incorrect!")
                        .build();
            } else {
                admin.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                return ApiResponse.<AdminEntity>builder()
                        .code(200)
                        .message("Change password successfully!")
                        .result(adminRepository.save(admin))
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
