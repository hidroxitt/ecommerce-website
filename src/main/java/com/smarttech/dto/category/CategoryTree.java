package com.smarttech.dto.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryTree {
    private Long id;
    private String code;
    private String name;
    private Boolean active;
    private Long parentId;
    private List<CategoryTree> child;
}
