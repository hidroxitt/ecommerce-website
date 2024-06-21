package vn.edu.hcmuaf.fit.shopzonerestfulapi.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ContactRequest;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response.ApiResponse;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.service.EmailService;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {
    private final EmailService emailService;

    @PostMapping("/send")
    public ApiResponse<String> sendContactEmail(@RequestBody ContactRequest contactRequest) throws MessagingException {
        emailService.sendContactForm(contactRequest);
        return ApiResponse.<String>builder()
                .message("Email sent successfully")
                .build();
    }
}
