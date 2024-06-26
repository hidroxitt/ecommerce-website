package com.smarttech.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SelfUpdateRequest {

    @Pattern(regexp = "(0|\\+84)\\d{9}", message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "Tên không được trống")
    private String firstName;

    @NotBlank(message = "Họ không được trống")
    private String lastName;
    private String address;

    public void copyProperties(UserResponse userResponse) {
        this.address = userResponse.getAddress();
        this.firstName = userResponse.getFirstName();
        this.lastName = userResponse.getLastName();
        this.phone = userResponse.getPhone();
    }
}
