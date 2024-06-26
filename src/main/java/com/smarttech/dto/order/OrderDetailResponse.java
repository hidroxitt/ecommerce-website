package com.smarttech.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailResponse {
    private Long id;
    private String productName;
    private String productCode;
    private String productImg;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer discount;
    private Integer quantity;
    private Integer orderId;
    private Long productDetailId;
    private String size;
    private String color;
}