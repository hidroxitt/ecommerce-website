package com.smarttech.dto.category;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryDTO extends CreateCategoryDTO {

    @NotNull(message = "id không thể trống")
    @Override
    public Long getId() {
        return super.getId();
    }
}
