package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
}
