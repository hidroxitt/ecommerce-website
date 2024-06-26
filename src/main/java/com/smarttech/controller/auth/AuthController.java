package com.smarttech.controller.auth;

import com.smarttech.constant.AppConstant;
import com.smarttech.dto.base.Result;
import com.smarttech.dto.user.UserRegisterRequest;
import com.smarttech.dto.user.UserResponse;
import com.smarttech.exception.BusinessException;
import com.smarttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userRegister", new UserRegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute @Valid UserRegisterRequest registerRequest, RedirectAttributes redirectAttributes) {
        try {
            Result<UserResponse> result = this.userService.registerUser(registerRequest);
            redirectAttributes.addFlashAttribute(AppConstant.ResponseKey.RESULT, result);
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage(), "redirect:/auth/register", Map.of("userRegister", registerRequest));
        }
        return "redirect:/auth";
    }
}
