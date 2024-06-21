package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
