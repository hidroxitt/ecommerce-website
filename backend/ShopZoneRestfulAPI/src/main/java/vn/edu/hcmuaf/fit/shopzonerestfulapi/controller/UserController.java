package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpdateUserRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpgradeToSellerRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.UserEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ApiResponse<UserEntity> update(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
            return userService.updateUser(userId, updateUserRequest);
    }

    @PutMapping("/upgradeToSeller/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<UserEntity> upgradeToSeller(@PathVariable Long userId, @RequestBody UpgradeToSellerRequest upgradeToSellerRequest) {
            return userService.upgradeToSeller(userId, upgradeToSellerRequest);
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponse<UserEntity> delete(@PathVariable Long userId) {
            return userService.deleteUser(userId);
    }
}
