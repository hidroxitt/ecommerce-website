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
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(SignupRequest signupRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userEntity.setFullName(signupRequest.getFullName());
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setPhone(signupRequest.getPhone());

        Role role = roleRepository.findByName("USER");
        userEntity.setRole(Collections.singleton(role));

        return userRepository.save(userEntity);
    }

    public UserEntity updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();

        if (!currentUserId.equals(userId)) {
            throw new RuntimeException("You are not allowed to update this user");
        } else {
            return userRepository.findById(userId).map(
                    user -> {
                        user.setFullName(updateUserRequest.getFullName());
                        user.setDob(updateUserRequest.getDob());
                        user.setEmail(updateUserRequest.getEmail());
                        user.setPhone(updateUserRequest.getPhone());
                        user.setAddress(updateUserRequest.getAddress());
                        return userRepository.save(user);
                    }
            ).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
        }
    }

    public UserEntity deleteUser(Long userId) {
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentUserId = currentUser.getId();
        if (!currentUserId.equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this user");
        } else {
            return userRepository.findById(userId).map(
                    user -> {
                        userRepository.delete(user);
                        return user;
                    }
            ).orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
        }

    }

    public UserEntity changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    public UserEntity getAllUser() {
        return null;
    }
}
