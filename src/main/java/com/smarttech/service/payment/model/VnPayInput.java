package com.smarttech.service.payment.model;

import lombok.Data;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
public class VnPayInput {
    private String vnp_Version;
    private String vnp_Command;
    private String vnp_TmnCode;
    private String vnp_Amount;
    private String vnp_CurrCode;
    private String vnp_TxnRef;
    private String vnp_OrderInfo;
    private String vnp_OrderType;
    private String vnp_Locale = "vn";
    private String vnp_ReturnUrl;
    private String vnp_IpAddr;
    private String vnp_CreateDate;
    private String vnp_ExpireDate;
    private String vnp_SecureHash;

    public String toQueryParam() {
        return new StringBuilder()
                .append("vnp_Amount=").append(this.encode(vnp_Amount))
                .append("&vnp_Command=").append(this.encode(vnp_Command))
                .append("&vnp_CreateDate=").append(this.encode(vnp_CreateDate))
                .append("&vnp_CurrCode=").append(this.encode(vnp_CurrCode))
                .append("&vnp_ExpireDate=").append(this.encode(vnp_ExpireDate))
                .append("&vnp_IpAddr=").append(this.encode(vnp_IpAddr))
                .append("&vnp_Locale=").append(this.encode(vnp_Locale))
                .append("&vnp_OrderInfo=").append(this.encode(vnp_OrderInfo))
                .append("&vnp_OrderType=").append(this.encode(vnp_OrderType))
                .append("&vnp_ReturnUrl=").append(this.encode(vnp_ReturnUrl))
                .append("&vnp_TmnCode=").append(this.encode(vnp_TmnCode))
                .append("&vnp_TxnRef=").append(this.encode(vnp_TxnRef))
                .append("&vnp_Version=").append(this.encode(vnp_Version))
                .toString();
    }

    public Object encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.US_ASCII.toString());
        } catch (Exception e) {
            return value;
        }
    }
}
