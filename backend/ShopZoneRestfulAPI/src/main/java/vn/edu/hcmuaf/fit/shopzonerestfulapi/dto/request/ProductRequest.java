package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private int discount;
    private LocalDate offerEnd;
    private boolean isNew;
    private int rating;
    private int saleCount;
    private List<Long> categoryIds;
    private List<VariationRequest> variations;
    private String shortDescription;
    private String fullDescription;
}
