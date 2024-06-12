package vn.edu.hcmuaf.fit.shopzonerestfulapi.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.UserRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ApiResponse<String> createUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ApiResponse.<String>builder()
                    .code(400)
                    .message("Username is already taken!")
                    .build();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setFullName(registerRequest.getFullName());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setPhone(registerRequest.getPhone());

        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Collections.singleton(role));
        userRepository.save(userEntity);

        return ApiResponse.<String>builder()
                .code(200)
                .message("User registered successfully!")
                .build();
    }
}
