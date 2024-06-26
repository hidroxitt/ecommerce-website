package com.smarttech.controller.user;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.cart.AddToCartRequest;
import com.smarttech.dto.cart.CartResponse;
import com.smarttech.dto.category.CategoryDTO;
import com.smarttech.dto.order.CreateOrderRequest;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.product.ProductDetailResponse;
import com.smarttech.dto.product.ProductResponse;
import com.smarttech.dto.product.ProductSearchRequest;
import com.smarttech.entity.Category;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.CartService;
import com.smarttech.service.CategoryService;
import com.smarttech.service.ProductService;
import com.smarttech.utils.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/mua-sam")
@RequiredArgsConstructor
public class ShoppingController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @GetMapping
    public String index(ProductSearchRequest productSearchRequest, Model model) {
        if (Objects.isNull(productSearchRequest)) {
            productSearchRequest = new ProductSearchRequest();
        }
        productSearchRequest.setActive(true);
        if (Objects.nonNull(productSearchRequest.getCategoryId())) {
            try {
                Category category = this.categoryService.findByIdThrowIfNotExist(productSearchRequest.getCategoryId());
                model.addAttribute("category", category);
            } catch (BusinessException ex) {
                ex.setRedirectUrl("redirect:/trang-chu");
                throw ex;
            }
        }
        productSearchRequest.setPageSize(16);
        model.addAttribute(SEARCH_MODEL_ATTRIBUTE, productSearchRequest);
        PageResponse<ProductResponse> productResponsePageResponse = this.productService.paginateProductList(productSearchRequest);
        List<CategoryDTO> activeCategory = this.categoryService.findActiveCategory();
        model.addAttribute("categories", activeCategory);
        this.addPagingResult(model, productResponsePageResponse);
        this.addCss(model, "/user/css/shop", "/user/css/product", "/user/css/paging");
        return "user/pages/product/index";
    }

    @GetMapping(value = "/chi-tiet-san-pham/{slug}")
    public String detail(@RequestParam String pCode, Model model) {
        try {
            ProductDetailResponse productDetailResponse = this.productService.getDetailProduct(pCode, AppConstant.NUMBER_BEST_SELLER_SHOW);
            model.addAttribute("product", productDetailResponse);
            model.addAttribute("addToCartRequest", new AddToCartRequest());
            this.addCss(model, "/user/css/shop", "/user/css/product", "/user/css/product-detail");
            this.addJavascript(model, "/user/javascript/product-detail");
            return "user/pages/product/detail";
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/trang-chu");
            throw ex;
        }
    }

    @GetMapping("/dat-hang")
    public String getDatHang(@RequestParam("cartId") List<Long> cartIds, Model model) {
        return this.datHang(cartIds, model);
    }

    @PostMapping("/dat-hang")
    public String datHang(@RequestParam("cartId") List<Long> cartIds, Model model) {
        List<CartResponse> checkoutProduct = this.cartService.getMyCart(cartIds);
        BigDecimal newPrice = new BigDecimal("0");
        BigDecimal oldPrice = new BigDecimal("0");

        for (CartResponse cp : checkoutProduct) {
            newPrice = CurrencyUtil.calculateDiscountPrice(cp.getPrice(), cp.getDiscount(), cp.getQuantity());
            oldPrice = CurrencyUtil.calculateOriginalPrice(cp.getPrice(), cp.getQuantity());
        }
        if (!model.containsAttribute("createOrderRequest")) {
            model.addAttribute("createOrderRequest", new CreateOrderRequest());
        }
        model.addAttribute("newPrice", newPrice);
        model.addAttribute("oldPrice", oldPrice);
        model.addAttribute("productList", checkoutProduct);
        this.addCss(model, "/user/css/checkout");
        return "/user/pages/checkout/index";
    }
}
