package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller.auth;

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
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth.UserRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.auth.AdminService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.auth.AuthService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.auth.UserService;

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
