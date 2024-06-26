package com.smarttech.controller.user;

import com.smarttech.constant.AppConstant;
import com.smarttech.constant.OrderStatus;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.order.OrderResponse;
import com.smarttech.dto.order.OrderSearchRequest;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/lich-su-mua-hang")
@RequiredArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @GetMapping
    public String index(OrderSearchRequest orderSearchRequest, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (orderSearchRequest == null) {
            orderSearchRequest = new OrderSearchRequest();
        }
        orderSearchRequest.setCreatedBy(userDetails.getUsername());
        model.addAttribute("searchRequest", orderSearchRequest);
        Map<String, Object> totalMoneyAndOrder = this.orderService.getTotalMoneyAndOrder();
        PageResponse<OrderResponse> paging = this.orderService.paginateOrderList(orderSearchRequest);

        model.addAttribute("totalOrder", totalMoneyAndOrder.get("totalOrder"));
        model.addAttribute("totalMoneyOrder", Optional.ofNullable(totalMoneyAndOrder.get("totalMoney")).orElse(AppConstant.ZERO));
        this.addPagingResult(model, paging);
        this.addCss(model, "/user/css/shopping-cart", "/user/css/paging", "/user/css/user-action");
        this.addJavascript(model, "/user/javascript/order");
        return "/user/pages/order/index";
    }

    @GetMapping("/chi-tiet")
    public String detail(@RequestParam String oCode, Model model) {
        try {
            OrderResponse orderResponse = this.orderService.getOrderResponse(oCode);
            model.addAttribute("orderResponse", orderResponse);
            return "/user/pages/order/order-detail";
        } catch (BusinessException exception) {
            exception.setRedirectUrl("redirect:/lich-su-mua-hang");
            throw exception;
        }
    }

    @PostMapping("/huy")
    public String cancelOrder(@RequestParam("oCode") String orderCode, @RequestParam("reasonCancel") String reasonCancel, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            this.orderService.changeStatusOrder(orderCode, reasonCancel, OrderStatus.CANCEL);
            Result<Object> result = Result.success(null, "Hủy đơn hàng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, result);
            return "redirect:/lich-su-mua-hang?" + request.getQueryString();
        } catch (BusinessException exception) {
            exception.setRedirectUrl("redirect:/lich-su-mua-hang?" + request.getQueryString());
            throw exception;
        }
    }
}
