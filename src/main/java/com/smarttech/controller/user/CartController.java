package com.smarttech.controller.user;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.cart.AddToCartRequest;
import com.smarttech.dto.cart.CartResponse;
import com.smarttech.dto.cart.UpdateCart;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gio-hang")
public class CartController extends BaseController {

    public static final String REDIRECT_HOME = "redirect:/gio-hang";

    private final CartService cartService;

    @GetMapping
    public String index(Model model) {
        List<CartResponse> myCart = this.cartService.getMyCart();
        model.addAttribute("myCart", myCart);
        this.addCss(model, "/user/css/shopping-cart");
        this.addJavascript(model, "/user/javascript/cart");
        return "user/pages/cart/index";
    }

    @PostMapping("/them-gio-hang")
    public String addToCart(@ModelAttribute @Valid AddToCartRequest request, RedirectAttributes redirectAttributes) {
        try {
            this.cartService.addToCart(request);
            Result<Object> result = Result.success(null, "Thêm giỏ hàng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, result);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }

    @GetMapping("/xoa-gio-hang")
    public String deleteCart(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            this.cartService.deleteById(id);
            Result<Object> result = Result.success(null, "Xóa khỏi giỏ hàng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, result);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }

    @GetMapping("/xoa-tat-ca")
    public String deleteAll(RedirectAttributes redirectAttributes) {
        this.cartService.deleteAll();
        Result<Object> success = Result.success(null, "Xóa khỏi giỏ hàng thành công");
        redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
        return REDIRECT_HOME;
    }

    @PostMapping(value = "/cap-nhat")
    public String updateCart(@RequestParam List<Long> id, @RequestParam List<Integer> quantity, RedirectAttributes redirectAttributes) {
        try {
            List<UpdateCart> updateCarts = new ArrayList<>();
            for (int i = 0; i < id.size(); i++) {
                UpdateCart updateCart = new UpdateCart();
                updateCart.setId(id.get(i));
                updateCart.setQuantity(quantity.get(i));
                updateCarts.add(updateCart);
            }
            this.cartService.updateCart(updateCarts);
            Result<Object> success = Result.success(null,  "Cập nhật giỏ hàng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException exception) {
            exception.setRedirectUrl(REDIRECT_HOME);
            throw exception;
        }
    }
}
