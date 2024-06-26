package com.smarttech.service.payment;

import com.smarttech.constant.OrderStatus;
import com.smarttech.dto.order.CreateOrderRequest;
import com.smarttech.dto.payment.PaymentResponse;
import com.smarttech.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CODPaymentService extends AbstractPaymentService {

    @Override
    public PaymentResponse payment(CreateOrderRequest createOrderRequest) {
        Order order = this.createOrder(createOrderRequest);
        order = this.orderDao.findById(order.getId()).orElseThrow();
        this.orderService.sendEmail(order);
        return PaymentResponse.builder()
                .url("redirect:/lich-su-mua-hang")
                .build();
    }

    @Override
    public void refund(Order order) {
        // un-support for COD payment
    }

    @Override
    public Order afterPayment(CreateOrderRequest createOrderRequest, Map<String, Object> data) {
        throw new UnsupportedOperationException("un-support for COD payment");
    }

    @Override
    public boolean isPaymentOnline() {
        return false;
    }
}
