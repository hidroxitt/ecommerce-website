package vn.edu.hcmuaf.fit.shopzonerestfulapi.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.request.ContactRequest;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setText(body, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("shopzoneecommerce@gmail.com"); // Corrected the email address
        javaMailSender.send(message);
    }

    public void sendContactForm(ContactRequest contactRequest) throws MessagingException {
        // Send email to support
        MimeMessage messageToSupport = javaMailSender.createMimeMessage();
        MimeMessageHelper helperToSupport = new MimeMessageHelper(messageToSupport, "utf-8");
        helperToSupport.setTo("shopzoneecommerce@gmail.com");
        helperToSupport.setSubject("Thông tin liên hệ mới từ website");
        helperToSupport.setText("Bạn có một thông tin liên hệ mới từ website. Chi tiết như sau:<br><br>"
                + "Tên: " + contactRequest.getName() + "<br>"
                + "Email: " + contactRequest.getEmail() + "<br>"
                + "Chủ đề: " + contactRequest.getSubject() + "<br>"
                + "Nội dung: " + contactRequest.getMessage(), true);
        javaMailSender.send(messageToSupport);

        // Send acknowledgment email to customer
        MimeMessage messageToCustomer = javaMailSender.createMimeMessage();
        MimeMessageHelper helperToCustomer = new MimeMessageHelper(messageToCustomer, "utf-8");
        helperToCustomer.setTo(contactRequest.getEmail());
        helperToCustomer.setSubject("Cảm ơn bạn đã liên hệ với chúng tôi");
        helperToCustomer.setText("Chúng tôi đã nhận được thông tin liên hệ của bạn. Chúng tôi sẽ phản hồi bạn trong thời gian sớm nhất.<br><br>"
                + "Chân thành cảm ơn!<br>"
                + "ShopZone Ecommerce", true);
        helperToCustomer.setFrom("shopzoneecommerce@gmail.com"); // Corrected the email address
        javaMailSender.send(messageToCustomer);
    }
}
