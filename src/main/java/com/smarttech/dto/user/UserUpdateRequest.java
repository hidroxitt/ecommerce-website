package com.smarttech.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest extends SelfUpdateRequest {

    @NotNull
    private Long id;
}
