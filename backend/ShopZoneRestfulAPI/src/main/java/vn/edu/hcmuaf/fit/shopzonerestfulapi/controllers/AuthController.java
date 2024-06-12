package vn.edu.hcmuaf.fit.shopzonerestfulapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.security.JwtTokenProvider;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.AdminSevice;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.AuthService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.SellerService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.UserService;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;
    private UserService userService;
    private AdminSevice adminSevice;
    private SellerService sellerService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register-user")
    public ApiResponse<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

    @PostMapping("/register-admin")
    public ApiResponse<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        return adminSevice.createAdmin(registerRequest);
    }

    @PostMapping("/register-seller")
    public ApiResponse<String> registerSeller(@RequestBody RegisterRequest registerRequest) {
        return sellerService.createSeller(registerRequest);
    }
}
