package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RefreshTokenRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.SellerRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.AdminService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.AuthService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.SellerService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(authService.login(request, loginRequest));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return authService.logout(request);
    }

    @PostMapping("/signup-admin")
    public ResponseEntity<AdminEntity> signupAdmin(@RequestBody SignupRequest signupRequest) {
        if (adminRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(adminService.createAdmin(signupRequest));
        }
    }

    @PostMapping("/signup-seller")
    public ResponseEntity<?> signupSeller(@RequestBody SignupRequest signupRequest) {
        if (sellerRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(sellerService.createSeller(signupRequest));
        }
    }

    @PostMapping("/signup-user")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userService.createUser(signupRequest));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
