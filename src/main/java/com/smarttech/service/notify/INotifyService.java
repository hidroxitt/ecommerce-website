package com.smarttech.service.notify;

import com.smarttech.service.notify.model.Notification;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public interface INotifyService {

    default void send(Notification notification) {
        this.send(notification, new HashMap<>());
    }

    void send(Notification notification, Map<String, Object> additionalInfo);

    @Async("notificationExecutor")
    default void asyncSend(Notification notification, Consumer<Map<String, Object>> consumer) {
        Map<String, Object> additionalInfo = new HashMap<>();
        consumer.accept(additionalInfo);
        this.send(notification, additionalInfo);
    }
}
