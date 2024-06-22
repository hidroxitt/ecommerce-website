package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
