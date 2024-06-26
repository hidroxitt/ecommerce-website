package com.smarttech.service.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VnPayQueryOutput {

    @JsonProperty("vnp_TransactionStatus")
    private String transactionStatus;

    @JsonProperty("vnp_TxnRef")
    private String txnRef;

}
