package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;

@Data
public class SliderRequest {
    private String title;
    private String subtitle;
    private String image;
    private String url;
}
