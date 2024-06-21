package vn.edu.hcmuaf.fit.shopzonerestfulapi.service.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.UserRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.EmailService;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public ApiResponse<UserEntity> createUser(SignupRequest signupRequest) {
        // Kiểm tra xem username đã tồn tại chưa
        if (userRepository.existsByUsername(signupRequest.getUsername()) || adminRepository.existsByUsername(signupRequest.getUsername())) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }
        // Tạo mới một UserEntity
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userEntity.setFullName(signupRequest.getFullName());
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setPhone(signupRequest.getPhone());

        // Lấy role USER từ roleRepository
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        userEntity.setRole(Collections.singleton(role));

        return ApiResponse.<UserEntity>builder()
                .code(200)
                .message("Create user successfully")
                .result(userRepository.save(userEntity))
                .build();
    }

    public ApiResponse<UserEntity> updateUser(Authentication authentication, UpdateUserRequest updateUserRequest) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("Username is not found")
                    .build();
        } else {
            UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found."));
            user.setFullName(updateUserRequest.getFullName());
            user.setDob(updateUserRequest.getDob());
            user.setEmail(updateUserRequest.getEmail());
            user.setPhone(updateUserRequest.getPhone());
            user.setAddress(updateUserRequest.getAddress());

            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Update user successfully")
                    .result(userRepository.save(user))
                    .build();
        }
    }

    public ApiResponse<UserEntity> upgradeToSeller(Authentication authentication, UpgradeToSellerRequest upgradeToSellerRequest) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("Username is not found")
                    .build();
        } else {
            UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found."));
            user.setFullName(upgradeToSellerRequest.getSellerName());
            user.setEmail(upgradeToSellerRequest.getEmail());
            user.setPhone(upgradeToSellerRequest.getPhone());
            user.setAddress(upgradeToSellerRequest.getAddress());
            user.setAvatar(upgradeToSellerRequest.getAvatar());

            // Lấy vai trò SELLER từ cơ sở dữ liệu
            Role sellerRole = roleRepository.findByName("SELLER").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            // Tạo một tập hợp vai trò mới chỉ chứa vai trò SELLER
            Set<Role> role = new HashSet<>(user.getRole());
            role.add(sellerRole);
            user.setRole(role);

            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Upgrade to seller successfully")
                    .result(userRepository.save(user))
                    .build();
        }
    }

    public ApiResponse<UserEntity> deleteUser(Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("Username is not found")
                    .build();
        } else {
            UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found."));
            userRepository.delete(user);
            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Delete user successfully")
                    .build();
        }
    }

    public ApiResponse<UserEntity> changePassword(Authentication authentication, ChangePasswordRequest changePasswordRequest) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("You are not logged in")
                    .build();
        } else {
            UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found."));
            if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
                return ApiResponse.<UserEntity>builder()
                        .code(400)
                        .message("Old password is incorrect")
                        .build();
            } else {
                user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

                return ApiResponse.<UserEntity>builder()
                        .code(200)
                        .message("Change password successfully")
                        .result(userRepository.save(user))
                        .build();

            }
        }
    }

    public String generateRandomPassword() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        int LENGTH_PASSWORD = 12;

        for (int i = 0; i < LENGTH_PASSWORD; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        return password.toString();
    }

    public ApiResponse<UserEntity> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws MessagingException {
        UserEntity user = userRepository.findByUsernameAndEmail(forgotPasswordRequest.getUsername(), forgotPasswordRequest.getEmail()).orElseThrow(() -> new RuntimeException("Error: User is not found."));

        String newPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        emailService.sendEmail(user.getEmail(), "New password", "Your new password is: " + newPassword);

        return ApiResponse.<UserEntity>builder()
                .code(200)
                .message("New password has been sent to your email")
                .result(userRepository.save(user))
                .build();
    }

    public ApiResponse<UserEntity> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("You are not logged in")
                    .build();
        } else {
            UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found."));
            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Get user info successfully")
                    .result(user)
                    .build();
        }
    }
}
