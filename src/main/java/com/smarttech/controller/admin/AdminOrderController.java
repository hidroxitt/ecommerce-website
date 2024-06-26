package com.smarttech.controller.admin;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController extends BaseController {

    @Value("${payment.vnpay.dashboard}")
    private String vnpayUrl;

    private final OrderService orderService;

    @GetMapping
    public String index(OrderSearchRequest searchRequest, Model model) {
        model.addAttribute("searchRequest", searchRequest);
        PageResponse<OrderResponse> paged = this.orderService.paginateOrderList(searchRequest);
        model.addAttribute("vnpayUrl", this.vnpayUrl);
        this.addPagingResult(model, paged);
        this.addCss(model, "/admin/css/user", "/admin/css/column-controller");
        this.addJavascript(model, "/admin/javascript/order", "/admin/javascript/column-controller");
        return "/admin/pages/order/index";
    }

    @GetMapping(value = "/change-status")
    public String changeStatusOrder(
            @RequestParam("status") OrderStatus status,
            @RequestParam("oCode") String orderCode,
            @RequestParam(value = "adminNote", required = false) String adminNote,
            RedirectAttributes redirectAttributes
    ) {
        try {
            this.orderService.changeStatusOrder(orderCode, adminNote, status);
            Result<Object> success = Result.success(null, "Thay đổi trạng thái đơn hàng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return "redirect:/admin/order";
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/admin/order");
            throw ex;
        }
    }
}
