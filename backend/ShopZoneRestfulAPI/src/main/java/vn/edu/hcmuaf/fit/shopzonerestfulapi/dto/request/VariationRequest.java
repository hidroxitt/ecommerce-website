package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class VariationRequest {
    private String color;
    private String image;
    private List<SizeRequest> sizes;
}
