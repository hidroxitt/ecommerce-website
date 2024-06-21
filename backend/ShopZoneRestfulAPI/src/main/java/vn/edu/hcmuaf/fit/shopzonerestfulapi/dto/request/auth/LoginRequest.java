package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
