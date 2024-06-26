package com.smarttech.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@EqualsAndHashCode(exclude = {"color", "image", "imageUrl"})
public class ColorProductDTO {
    private Long id;
    private String color;
    private MultipartFile image;
    private String imageUrl;
}
