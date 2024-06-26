package com.smarttech.service.payment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payment.vnpay")
@ConditionalOnProperty(prefix = "payment.vnpay", value = "enable")
@Setter
@Getter
public class VnPayProperties {
    private Boolean enable;
    private String url;
    private String api;
    private String tmnCode;
    private String secretKey;
    private String currency;
    private Integer ttl;
    private String version;
}
