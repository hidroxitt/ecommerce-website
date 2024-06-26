package com.smarttech.service.payment;

import com.smarttech.dto.order.CreateOrderRequest;
import com.smarttech.dto.payment.PaymentResponse;
import com.smarttech.entity.Order;

import java.util.Map;

public interface IPaymentService {
    PaymentResponse payment(CreateOrderRequest createOrderRequest);
    Order afterPayment(CreateOrderRequest createOrderRequest, Map<String, Object> data);
    void refund(Order order);
    boolean isPaymentOnline();
}
