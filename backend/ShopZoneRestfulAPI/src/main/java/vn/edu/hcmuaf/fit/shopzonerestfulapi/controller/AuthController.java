package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RefreshTokenRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.SignupRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.UserRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.AdminService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.AuthService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ApiResponse.<AuthResponse>builder()
                    .code(400)
                    .message("Username and password are required")
                    .build();
        } else {
            return authService.login(request, loginRequest);
        }
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        return authService.logout(request);
    }

    @PostMapping("/signup-admin")
    public ApiResponse<AdminEntity> signupAdmin(@RequestBody SignupRequest signupRequest) {
        return adminService.createAdmin(signupRequest);
    }

    @PostMapping("/signup-user")
    public ApiResponse<UserEntity> signupUser(@RequestBody SignupRequest signupRequest) {
        return userService.createUser(signupRequest);
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
