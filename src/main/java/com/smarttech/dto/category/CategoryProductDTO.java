package com.smarttech.dto.category;

import com.smarttech.dto.product.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class CategoryProductDTO extends CategoryDTO {
    private List<CategoryDTO> child;
    private List<ProductResponse> products;
}
