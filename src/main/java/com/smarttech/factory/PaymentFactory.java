package com.smarttech.factory;

import com.smarttech.constant.MethodPayment;
import com.smarttech.exception.ValidationException;
import com.smarttech.service.payment.IPaymentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFactory {

    private final ApplicationContext context;

    @NonNull
    public IPaymentService getInstance(MethodPayment method) {
        try {
            return this.context.getBean(method.implementClass);
        } catch (Exception ex) {
            throw new ValidationException("Phương thức thanh toán " + method.label + " đang phát triển.");
        }
    }
}
