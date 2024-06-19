package vn.edu.hcmuaf.fit.shopzonerestfulapi.service.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.*;
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
        // Kiểm tra xem username đã tồn tại trong userRepository chưa
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }

        // Kiểm tra xem username đã tồn tại trong adminRepository chưa
        if (adminRepository.existsByUsername(signupRequest.getUsername())) {
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
        Role role = roleRepository.findByName("USER");
        userEntity.setRole(Collections.singleton(role));

        userRepository.save(userEntity);

        return ApiResponse.<UserEntity>builder()
                .code(200)
                .message("Create user successfully")
                .result(userEntity)
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
            UserEntity user = userRepository.findByUsername(username);
            user.setFullName(updateUserRequest.getFullName());
            user.setDob(updateUserRequest.getDob());
            user.setEmail(updateUserRequest.getEmail());
            user.setPhone(updateUserRequest.getPhone());
            user.setAddress(updateUserRequest.getAddress());
            userRepository.save(user);

            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Update user successfully")
                    .result(user)
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
            UserEntity user = userRepository.findByUsername(username);
            user.setFullName(upgradeToSellerRequest.getSellerName());
            user.setEmail(upgradeToSellerRequest.getEmail());
            user.setPhone(upgradeToSellerRequest.getPhone());
            user.setAddress(upgradeToSellerRequest.getAddress());
            user.setAvatar(upgradeToSellerRequest.getAvatar());

            // Lấy vai trò SELLER từ cơ sở dữ liệu
            Role sellerRole = roleRepository.findByName("SELLER");
            // Tạo một tập hợp vai trò mới chỉ chứa vai trò SELLER
            Set<Role> role = new HashSet<>(user.getRole());
            role.add(sellerRole);
            user.setRole(role);
            userRepository.save(user);

            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Upgrade to seller successfully")
                    .result(user)
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
            UserEntity user = userRepository.findByUsername(username);
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
            UserEntity user = userRepository.findByUsername(username);
            if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
                return ApiResponse.<UserEntity>builder()
                        .code(400)
                        .message("Old password is incorrect")
                        .build();
            } else {
                user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userRepository.save(user);
                return ApiResponse.<UserEntity>builder()
                        .code(200)
                        .message("Change password successfully")
                        .result(user)
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
        UserEntity user = userRepository.findByUsernameAndEmail(forgotPasswordRequest.getUsername(), forgotPasswordRequest.getEmail());
        if (user == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("Username or email is incorrect")
                    .build();
        } else {
            String newPassword = generateRandomPassword();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            emailService.sendEmail(user.getEmail(), "New password", "Your new password is: " + newPassword);
            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("New password has been sent to your email")
                    .result(user)
                    .build();
        }
    }

    public ApiResponse<UserEntity> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("You are not logged in")
                    .build();
        } else {
            UserEntity user = userRepository.findByUsername(username);
            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Get user info successfully")
                    .result(user)
                    .build();
        }
    }
}
