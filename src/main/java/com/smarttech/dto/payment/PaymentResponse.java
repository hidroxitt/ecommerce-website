package com.smarttech.dto.payment;

import com.smarttech.entity.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String url;
}
