package com.smarttech.service.notify.model;

import com.smarttech.constant.NotificationConstant;
import lombok.Data;

@Data
public class Notification {
    private String destination;
    private String from;
    private String title;
    private NotificationConstant.Template template;
}
