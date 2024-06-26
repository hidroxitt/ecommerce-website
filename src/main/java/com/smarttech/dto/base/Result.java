package com.smarttech.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private boolean isSuccess;
    private String message;
    private T data;
    private String successUrl;
    private String failUrl;

    public static <T> Result<T> success(T data, String message) {
        return Result.<T>builder()
                .isSuccess(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Result<T> fail(T data, String message) {
        return Result.<T>builder()
                .isSuccess(false)
                .data(data)
                .message(message)
                .build();
    }
}
