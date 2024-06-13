package vn.edu.hcmuaf.fit.shopzonerestfulapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.RegisterRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.UpdateUserRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.services.UserService;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

    @PutMapping("/update/{userId}")
    public ApiResponse<String> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest){
        return userService.updateUser(userId, updateUserRequest);
    }

}
