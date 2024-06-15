package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

@Data
public class UpgradeToSellerRequest {
    private String sellerName;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
