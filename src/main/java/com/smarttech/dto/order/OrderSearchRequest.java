package com.smarttech.dto.order;

import com.smarttech.dto.page.PageRequest;
import lombok.Data;

@Data
public class OrderSearchRequest extends PageRequest {
    private String code;
    private String createdBy;
    private String methodPayment;
    private String fromDate;
    private String toDate;
    private String phone;
    private String address;
    private String status;
}