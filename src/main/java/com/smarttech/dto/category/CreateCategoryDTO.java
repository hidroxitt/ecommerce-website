package com.smarttech.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCategoryDTO {

    private Long id;

    @NotBlank(message = "Mã danh mục không được trống")
    private String code;

    @NotBlank(message = "Tên mục danh mục không được trống")
    private String name;

    private Long parentId;
}
