package vn.edu.hcmuaf.fit.shopzonerestfulapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthenticationResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.SellerEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.RoleRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.SellerRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories.UserRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.security.JwtTokenProvider;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private SellerRepository sellerRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        AuthenticationResponse authResponse = new AuthenticationResponse(token);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .message("Login successful!")
                .result(authResponse)
                .build();
    }

    @PostMapping("/register-user")
    public ApiResponse<String> registerUser(@RequestBody RegisterRequest registerRequest) {
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

    @PostMapping("/register-admin")
    public ApiResponse<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
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

    @PostMapping("/register-seller")
    public ApiResponse<String> registerSeller(@RequestBody RegisterRequest registerRequest) {
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
