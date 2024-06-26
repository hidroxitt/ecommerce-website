package com.smarttech.controller.admin;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.category.CategoryDTO;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.product.CreateProductRequest;
import com.smarttech.dto.product.ProductResponse;
import com.smarttech.dto.product.ProductSearchRequest;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.CategoryService;
import com.smarttech.service.ProductDetailService;
import com.smarttech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController extends BaseController {

    public static final String CATEGORIES_LIST_KEY = "categories";
    public static final String REDIRECT_HOME = "redirect:/admin/product";

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @GetMapping
    public String index(Model model, ProductSearchRequest pagedRequest) {
        model.addAttribute("searchRequest", pagedRequest);
        PageResponse<ProductResponse> productResponse = this.productService.paginateProductList(pagedRequest);
        model.addAttribute(CATEGORIES_LIST_KEY, this.categoryService.findAll());
        this.addPagingResult(model, productResponse);
        this.addCss(model, "/admin/css/column-controller", "/admin/css/user");
        this.addJavascript(model, "/admin/javascript/column-controller");
        return "/admin/pages/product/index";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        if (!model.containsAttribute(CREATION_MODEL_ATTRIBUTE)) {
            model.addAttribute(CREATION_MODEL_ATTRIBUTE, new CreateProductRequest());
        }
        List<CategoryDTO> categories = this.categoryService.findActiveChildCategory();
        model.addAttribute(CATEGORIES_LIST_KEY, categories);
        model.addAttribute("isUpdate", false);
        this.addJavascript(model, "/admin/javascript/core-ckeditor/ckeditor", "/admin/javascript/ckeditor", "/admin/javascript/create-product");
        this.addCss(model, "/admin/css/create-product");
        return "/admin/pages/product/create-product";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute @Valid CreateProductRequest createRequest, @RequestParam Map<String, MultipartFile> fileMap, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        try {
            createRequest.setColorSize(fileMap, req);
            this.productService.createProduct(createRequest);
            Result<Object> success = Result.success(null, "Tạo sản phẩm thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/admin/product/create");
            ex.setData(Map.of(CREATION_MODEL_ATTRIBUTE, createRequest));
            throw ex;
        }
    }

    @GetMapping("/update")
    public String updateProduct(@RequestParam("pCode") String productCode, Model model) {
        ProductResponse detailProduct = this.productDetailService.getDetailProduct(productCode);
        detailProduct.setColorsAndSize();
        List<CategoryDTO> categories = this.categoryService.findActiveChildCategory();
        model.addAttribute(CATEGORIES_LIST_KEY, categories);
        model.addAttribute(CREATION_MODEL_ATTRIBUTE, detailProduct);
        model.addAttribute("isUpdate", true);
        this.addJavascript(model, "/admin/javascript/core-ckeditor/ckeditor", "/admin/javascript/ckeditor", "/admin/javascript/create-product");
        this.addCss(model, "/admin/css/create-product");
        return "/admin/pages/product/create-product";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid CreateProductRequest createRequest, @RequestParam Map<String, MultipartFile> fileMap, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        try {
            createRequest.setColorSize(fileMap, req);
            this.productService.updateProduct(createRequest);
            Result<Object> success = Result.success(null, "Cập nhật sản phẩm thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/admin/product/update?pCode" + createRequest.getCode());
            ex.setData(Map.of(CREATION_MODEL_ATTRIBUTE, createRequest));
            throw ex;
        }
    }

    @GetMapping("/lock")
    public String lockOrUnlockProduct(@RequestParam String code, RedirectAttributes redirectAttributes) {
        try {
            this.productService.lockOrUnlockProduct(code);
            Result<Object> success = Result.success(null, "Thay đổi trạng thái sản phẩm thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }
}
