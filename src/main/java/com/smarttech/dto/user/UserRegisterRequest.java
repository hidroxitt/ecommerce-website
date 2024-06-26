package com.smarttech.dto.user;

import com.smarttech.constant.RoleConstant;
import com.smarttech.validation.annotation.RepeatPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@RepeatPassword(message = "user.password.validate.not-match")
public class UserRegisterRequest {

    private Long id;

    @NotBlank(message = "user.firstname.validate.empty")
    private String firstName;

    @NotBlank(message = "user.lastname.validate.empty")
    private String lastName;

    @NotBlank(message = "user.username.validate.empty")
    private String email;

    @NotBlank(message = "user.password.validate.empty")
    private String password;

    @NotBlank(message = "user.repeat-password.validate.empty")
    private String repeatPassword;

    private String phone;
    private String address;

    public RoleConstant getRole() {
        return RoleConstant.USER;
    }
}
