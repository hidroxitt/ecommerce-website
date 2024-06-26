package com.smarttech.controller.user;

import com.smarttech.constant.AppConstant;
import com.smarttech.constant.MethodPayment;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.order.CreateOrderRequest;
import com.smarttech.dto.payment.PaymentResponse;
import com.smarttech.entity.Order;
import com.smarttech.exception.BusinessException;
import com.smarttech.exception.ValidationException;
import com.smarttech.factory.PaymentFactory;
import com.smarttech.service.OrderService;
import com.smarttech.service.payment.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/thanh-toan")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFactory paymentFactory;
    private final OrderService orderService;

    @PostMapping
    public String createOrder(
        @ModelAttribute @Valid CreateOrderRequest createOrderRequest,
        @AuthenticationPrincipal UserDetails userDetails,
        HttpSession httpSession,
        RedirectAttributes redirectAttributes,
        HttpServletRequest request
    ) {
        try {
            IPaymentService instance = this.paymentFactory.getInstance(createOrderRequest.getMethod());
            if (instance.isPaymentOnline()) {
                createOrderRequest.setUuid(UUID.randomUUID().toString());
                createOrderRequest.setIpAddress(this.getIpAddress(request));
            }
            createOrderRequest.setCreatedBy(userDetails.getUsername());
            PaymentResponse result = instance.payment(createOrderRequest);
            if (instance.isPaymentOnline()) {
                httpSession.setAttribute(createOrderRequest.getUuid(), createOrderRequest);
            }
            Result<Object> success = Result.success(null, "Đặt hàng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return result.getUrl();
        } catch (BusinessException ex) {
            String ids = createOrderRequest.getCartId()
                    .stream()
                    .map(id -> String.format("cartId=%s", id))
                    .collect(Collectors.joining("&"));
            ex.setRedirectUrl("redirect:/mua-sam/dat-hang?" + ids);
            ex.setData(Map.of("createOrderRequest", createOrderRequest));
            throw ex;
        }
    }

    @GetMapping("/thanh-cong")
    public String paymentSuccess(
            @RequestParam String provider,
            @RequestParam String uuid,
            @RequestParam Map<String, Object> reqParam,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Object dataCreateOrder = session.getAttribute(uuid);
            if (Objects.isNull(dataCreateOrder)) throw new ValidationException("Hóa đơn chưa được tạo");
            CreateOrderRequest createOrderRequest = (CreateOrderRequest) dataCreateOrder;
            MethodPayment methodPayment = MethodPayment.valueOf(provider);
            IPaymentService instance = this.paymentFactory.getInstance(methodPayment);
            Order order = instance.afterPayment(createOrderRequest, reqParam);
            this.orderService.sendEmail(order);
            Result<Object> result = Result.success(null, "Thanh toán thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, result);
            return "redirect:/lich-su-mua-hang";
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/lich-su-mua-hang");
            throw ex;
        }
    }

    public String getIpAddress(HttpServletRequest request) {
        try {
            return Optional.ofNullable(request.getHeader("X-FORWARDED-FOR"))
                    .orElseGet(request::getRemoteAddr);
        } catch (Exception e) {
            return "Invalid IP:" + e.getMessage();
        }
    }
}