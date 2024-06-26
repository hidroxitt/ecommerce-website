package com.smarttech.controller.user;

import com.smarttech.constant.AppConstant;
import com.smarttech.controller.BaseController;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.user.ChangePasswordDTO;
import com.smarttech.dto.user.SelfUpdateRequest;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/tai-khoan")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping("/cap-nhat")
    public String updateUser(@ModelAttribute @Valid SelfUpdateRequest selfUpdateRequest, RedirectAttributes redirectAttributes) {
        try {
            this.userService.updateSelf(selfUpdateRequest);
            Result<Object> success = Result.success(null, "Cập nhật thông tin tài khoản thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return "redirect:/ho-so/tai-khoan";
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/ho-so/tai-khoan");
            ex.setData(Map.of("userUpdateRequest", selfUpdateRequest));
            throw ex;
        }
    }

    @PostMapping("/doi-mat-khau")
    public String changePassword(@ModelAttribute @Valid ChangePasswordDTO changePassword, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        try {
            this.userService.changePassword(changePassword);
            httpSession.invalidate();
            Result<Object> success = Result.success(null, "Thay đổi mật khẩu thành công");
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, success);
            return "redirect:/auth";
        } catch (BusinessException ex) {
            ex.setRedirectUrl("redirect:/ho-so/doi-mat-khau");
            ex.setData(Map.of("changePassword", changePassword));
            throw ex;
        }
    }
}
