package com.smarttech.service.payment.model;

import lombok.Data;

@Data
public class VnPayQueryInput extends VnPayInput {
    private String vnp_TransactionDate;
    private String vnp_RequestId;
    private String vnp_SecureHash;

    @Override
    public String getVnp_Command() {
        return "querydr";
    }
}
