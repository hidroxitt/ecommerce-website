package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String refreshToken;
}
