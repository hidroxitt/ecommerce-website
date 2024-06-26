package com.smarttech.controller.user;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.category.CategoryProductDTO;
import com.smarttech.dto.product.ProductImageDTO;
import com.smarttech.dto.product.ProductResponse;
import com.smarttech.dto.user.ChangePasswordDTO;
import com.smarttech.dto.user.SelfUpdateRequest;
import com.smarttech.dto.user.UserResponse;
import com.smarttech.service.CategoryService;
import com.smarttech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping({AppConstant.Endpoint.HOME,"/"})
    public String index(Model model) {
        List<ProductImageDTO> productImage = this.productService.getProductImage(AppConstant.NUMBER_IMAGE_SHOW_HOME);
        List<ProductResponse> bestSeller = this.productService.getBestSeller(AppConstant.NUMBER_BEST_SELLER_SHOW);
        List<ProductResponse> newest = this.productService.getNewest(AppConstant.NUMBER_BEST_SELLER_SHOW);
        List<ProductResponse> hotSales = this.productService.getHotSales(AppConstant.NUMBER_BEST_SELLER_SHOW);

        List<CategoryProductDTO> categoryProduct = this.categoryService.findCategoryProduct(AppConstant.NUMBER_BEST_SELLER_SHOW);
        model.addAttribute("categoryProduct", categoryProduct);
        model.addAttribute("productImage", productImage);
        model.addAttribute("bestSeller", bestSeller);
        model.addAttribute("newest", newest);
        model.addAttribute("hotSales", hotSales);
        this.addCss(model, "/user/css/home", "/user/css/product", "/user/css/banner", "/user/css/hero");
        this.addJavascript(model, "/user/javascript/home");
        return "user/pages/home/index";
    }

    @GetMapping("/lien-he")
    public String contact(Model model) {
        this.addCss(model, "/user/css/contact", "/user/css/map");
        return "user/pages/contact/index";
    }

    @GetMapping("/ho-so/tai-khoan")
    public String profileAccount(@AuthenticationPrincipal UserResponse userResponse, Model model) {
        if (!model.containsAttribute("selfUpdateRequest")) {
            SelfUpdateRequest selfUpdateRequest = new SelfUpdateRequest();
            selfUpdateRequest.copyProperties(userResponse);
            model.addAttribute("selfUpdateRequest", selfUpdateRequest);
        }
        return "/user/pages/user/index";
    }

    @GetMapping("/ho-so/doi-mat-khau")
    public String profileChangePassword(Model model) {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
        model.addAttribute("changePassword", changePasswordDTO);
        return "/user/pages/user/change-password";
    }
}