package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private int discount;
    private LocalDate offerEnd;
    private boolean isNew;
    private int rating;
    private int saleCount;
    private Set<Long> categoryIds;
    private List<Long> variationIds;
    private List<MultipartFile> images;
    private String shortDescription;
    private String fullDescription;
}
