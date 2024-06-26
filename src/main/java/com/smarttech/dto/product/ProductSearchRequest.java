package com.smarttech.dto.product;

import com.smarttech.dto.page.PageRequest;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

@Data
public class ProductSearchRequest extends PageRequest {
    private String codeName;
    private Long categoryId;
    private String fromDate;
    private String toDate;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String size;
    private Boolean active;
    private Sort.Direction sort;
    private String columnSort;
}
