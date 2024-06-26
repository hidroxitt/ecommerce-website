package com.smarttech.dto.user;

import com.smarttech.validation.annotation.RepeatPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@RepeatPassword(fields = {"newPassword", "repeatPassword"}, message = "Mật khẩu không trùng nhau")
public class ChangePasswordDTO {

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String repeatPassword;
}
