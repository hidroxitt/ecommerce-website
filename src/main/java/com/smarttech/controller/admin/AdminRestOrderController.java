package com.smarttech.controller.admin;

import com.smarttech.dto.order.OrderResponse;
import com.smarttech.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/rest/order")
@RequiredArgsConstructor
public class AdminRestOrderController {

    private final OrderService orderService;

    @GetMapping
    public OrderResponse getOrderDetailByOrderId(@RequestParam String orderId) {
        return this.orderService.findByCode(orderId);
    }

}
