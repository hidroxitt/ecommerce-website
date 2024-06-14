package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String username;
    private String email;
}
