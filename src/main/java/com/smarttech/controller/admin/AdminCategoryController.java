package com.smarttech.controller.admin;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.category.CategoryDTO;
import com.smarttech.dto.category.CategoryTree;
import com.smarttech.dto.category.CreateCategoryDTO;
import com.smarttech.dto.category.UpdateCategoryDTO;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController extends BaseController {

    public static final String REDIRECT_HOME = "redirect:/admin/category";

    private final CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<CategoryTree> categoryTree = this.categoryService.findAllTree();
        List<CategoryDTO> roots = this.categoryService.findActiveByParentId(null);
        model.addAttribute("roots", roots);
        model.addAttribute("categoryTree", categoryTree);
        model.addAttribute("createCategory", new CreateCategoryDTO());
        this.addCss(model, "/admin/css/user");
        this.addJavascript(model, "/admin/javascript/category");
        return "admin/pages/category/index";
    }

    @PostMapping("/create")
    public String create(@Valid CreateCategoryDTO createCategoryDTO, RedirectAttributes redirectAttributes) {
        try {
            this.categoryService.createCategory(createCategoryDTO);
            Result<Object> success = Result.success(null, "Tạo danh mục thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }

    @PostMapping("/update")
    public String update(@Valid UpdateCategoryDTO updateCategoryDTO, RedirectAttributes redirectAttributes) {
        try {
            this.categoryService.updateCategory(updateCategoryDTO);
            Result<Object> success = Result.success(null, "Cập nhật danh mục thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }

    @GetMapping("/change-status")
    public String changeStatus(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            this.categoryService.changeStatus(id);
            Result<Object> success = Result.success(null, "Thay đổi trạng thái thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            this.categoryService.deleteCategory(id);
            Result<Object> success = Result.success(null, "Xóa danh mục thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }
}
