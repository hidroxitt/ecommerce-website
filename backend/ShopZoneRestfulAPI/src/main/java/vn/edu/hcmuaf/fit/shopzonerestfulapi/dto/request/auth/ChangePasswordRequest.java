package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.auth;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
