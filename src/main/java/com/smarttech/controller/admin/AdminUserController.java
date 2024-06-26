package com.smarttech.controller.admin;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.user.UserSearchRequest;
import com.smarttech.dto.user.UserSearchResponse;
import com.smarttech.dto.user.UserUpdateRequest;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController extends BaseController {

    public static final String REDIRECT_HOME = "redirect:/admin/user";

    private final UserService userService;

    @GetMapping
    public String index(UserSearchRequest searchRequest, Model model) {
        model.addAttribute("searchRequest", searchRequest);
        PageResponse<UserSearchResponse> pageResponse = this.userService.searchUser(searchRequest);
        this.addPagingResult(model, pageResponse);
        this.addCss(model, "/admin/css/user");
        return "/admin/pages/user/index";
    }

    @GetMapping("/lock")
    public String lockOrUnlockUser(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            this.userService.changeStatus(id);
            Result<Object> success = Result.success(null, "Thay đổi trạng thái thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException ex) {
            ex.setRedirectUrl(REDIRECT_HOME);
            throw ex;
        }
    }

    @PostMapping("/update")
    public String updateUser(@Valid UserUpdateRequest request, RedirectAttributes redirectAttributes) {
        try {
            this.userService.updateUser(request);
            Result<Object> success = Result.success(null, "Cập nhật người dùng thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return REDIRECT_HOME;
        } catch (BusinessException exception) {
            exception.setRedirectUrl(REDIRECT_HOME);
            throw exception;
        }
    }
}
