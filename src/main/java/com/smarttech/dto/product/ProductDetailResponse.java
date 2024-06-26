package com.smarttech.dto.product;

import com.smarttech.entity.Specifications;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
public class ProductDetailResponse extends ProductResponse {
    private List<ProductResponse> relatedProducts;
    private Set<String> sizesSet;
    private List<Specifications> specifications;
}
