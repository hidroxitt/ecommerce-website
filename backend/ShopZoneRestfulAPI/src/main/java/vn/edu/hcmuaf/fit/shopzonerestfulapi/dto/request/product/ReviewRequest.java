package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.product;

import lombok.Data;

@Data
public class ReviewRequest {
    private String comment;
    private int rating;
}
