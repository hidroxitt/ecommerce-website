package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

@Data
public class ContactRequest {
    private String name;
    private String email;
    private String subject;
    private String message;
}
