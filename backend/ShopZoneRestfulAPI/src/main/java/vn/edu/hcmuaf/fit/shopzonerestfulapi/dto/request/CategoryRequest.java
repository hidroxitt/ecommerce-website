package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String image;
    private String link;
}
