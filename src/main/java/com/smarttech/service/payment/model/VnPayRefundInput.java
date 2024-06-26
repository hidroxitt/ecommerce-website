package com.smarttech.service.payment.model;

import lombok.Data;

@Data
public class VnPayRefundInput extends VnPayInput {
    private String vnp_RequestId;
    private String vnp_TransactionType;
    private String vnp_TransactionDate;
    private String vnp_CreateBy;

    @Override
    public String getVnp_Command() {
        return "refund";
    }
}
