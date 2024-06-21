package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String username;
    private String email;
}
