package com.smarttech.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "Mã loại sản phẩm không được trống!")
    private String code;

    @NotBlank(message = "Tên loại sản phẩm không được trống!")
    private String name;
    private Boolean active;
}