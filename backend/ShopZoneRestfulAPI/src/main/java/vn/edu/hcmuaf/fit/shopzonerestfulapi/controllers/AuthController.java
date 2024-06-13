package vn.edu.hcmuaf.fit.shopzonerestfulapi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.LoginRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.AuthResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.AdminService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.AuthService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.SellerService;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.UserService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        return authService.login(request ,loginRequest);
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        return authService.logout(request);
    }

}
