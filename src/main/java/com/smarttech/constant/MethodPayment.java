package com.smarttech.constant;

import com.smarttech.service.payment.CODPaymentService;
import com.smarttech.service.payment.IPaymentService;
import com.smarttech.service.payment.VNPPaymentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MethodPayment {
    COD("COD", CODPaymentService.class),
//    PAYPAL("Ví Paypal", PaypalPaymentService.class),
    VNPAY("Ví Vnpay", VNPPaymentService.class);

    public final String label;
    public final Class<? extends IPaymentService> implementClass;
}
