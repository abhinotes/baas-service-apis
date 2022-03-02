package com.abhinotes.baas.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Builder
@Data
@ToString
public class Payment {

    private String requestId;
    private String debitAccount;
    private String creditAccount;
    private String txnCcy;
    private float amount;
    private String txnReference;
    private String status;
    private Date timestamp;

}
