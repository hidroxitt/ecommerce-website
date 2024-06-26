package com.smarttech.service.notify.impl;

import com.smarttech.service.notify.INotifyService;
import com.smarttech.service.notify.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;

@Service("MailNotifyService")
@RequiredArgsConstructor
@Slf4j
public class MailNotifyService implements INotifyService {

    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;

    @Override
    public void send(Notification notification, Map<String, Object> additionalInfo) {
        try {
            log.info("send mail notification");
            String templatePath = notification.getTemplate().getTemplatePath();
            Context context = new Context();
            context.setVariables(additionalInfo);
            String html = this.templateEngine.process(templatePath, context);

            MimeMessage mimeMessage = this.sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(notification.getFrom());
            helper.setTo(notification.getDestination());
            helper.setSentDate(new Date());
            helper.setText(html, true);
            helper.setValidateAddresses(true);
            helper.setSubject(notification.getTitle());
            this.sender.send(mimeMessage);
            log.info("send mail notification success");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
