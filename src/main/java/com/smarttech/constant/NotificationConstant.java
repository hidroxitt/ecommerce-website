package com.smarttech.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConstant {

    public interface Template {
        String getTemplatePath();
    }

    @RequiredArgsConstructor
    @Getter
    public enum MailTemplate implements Template {
        ORDER("/mail/order/index"),
        REGISTER_ACC("/mail/user/register");

        private final String templatePath;
    }
}
