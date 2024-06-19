package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ChangePasswordRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.AdminRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/change-password")
    public ApiResponse<AdminEntity> changePassword(Authentication authentication, @RequestBody ChangePasswordRequest changePasswordRequest) {
        return adminService.changePassword(authentication, changePasswordRequest);
    }

    @GetMapping("/users")
    public ApiResponse<List<UserEntity>> getAllUsers(Authentication authentication) {
        return adminService.getAllUsers(authentication);
    }
}
