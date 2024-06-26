package com.smarttech.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smarttech.constant.MethodPayment;
import com.smarttech.constant.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private int id;
    private String code;
    private MethodPayment methodPayment;
    private String fullName;
    private String phone;
    private String address;
    private String createdBy;
    private Date createdDate;
    private BigDecimal total;
    private OrderStatus status;
    private String adminNote;
    private String userNote;
    private List<OrderDetailResponse> orderDetails;
}
