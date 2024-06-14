package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpdateUserRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpgradeToSellerRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;

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

        userEntity = userRepository.save(userEntity);

        return ApiResponse.<UserEntity>builder()
                .code(200)
                .message("Create user successfully")
                .result(userEntity)
                .build();
    }

    public ApiResponse<UserEntity> updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();

        if (!currentUserId.equals(userId)) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("You are not allowed to update this user")
                    .build();
        } else {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
            user.setFullName(updateUserRequest.getFullName());
            user.setDob(updateUserRequest.getDob());
            user.setEmail(updateUserRequest.getEmail());
            user.setPhone(updateUserRequest.getPhone());
            user.setAddress(updateUserRequest.getAddress());
            user = userRepository.save(user);

            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Update user successfully")
                    .result(user)
                    .build();
        }
    }

    public ApiResponse<UserEntity> upgradeToSeller(Long userId, UpgradeToSellerRequest upgradeToSellerRequest) {
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();

        if (!currentUserId.equals(userId)) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("You are not allowed to upgrade this user")
                    .build();
        } else {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
            user.setFullName(upgradeToSellerRequest.getSellerName());
            user.setEmail(upgradeToSellerRequest.getEmail());
            user.setPhone(upgradeToSellerRequest.getPhone());
            user.setAddress(upgradeToSellerRequest.getAddress());
            user.setAvatar(upgradeToSellerRequest.getAvatar());

            // Lấy vai trò SELLER từ cơ sở dữ liệu
            Role sellerRole = roleRepository.findByName("SELLER");
            // Tạo một tập hợp vai trò mới chỉ chứa vai trò SELLER
            Set<Role> role = new HashSet<>();
            role.add(sellerRole);
            user.setRole(role);
            user = userRepository.save(user);

            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Upgrade to seller successfully")
                    .result(user)
                    .build();
        }
    }

    public ApiResponse<UserEntity> deleteUser(Long userId) {
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();
        if (!currentUserId.equals(userId)) {
            return ApiResponse.<UserEntity>builder()
                    .code(400)
                    .message("You are not allowed to delete this user")
                    .build();
        } else {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
            userRepository.delete(user);
            return ApiResponse.<UserEntity>builder()
                    .code(200)
                    .message("Delete user successfully")
                    .build();
        }
    }

    public UserEntity changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    public UserEntity getAllUser() {
        return null;
    }
}
