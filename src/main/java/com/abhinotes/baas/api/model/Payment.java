package com.abhinotes.baas.api.model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    @NonNull
    private String requestId;
    @NonNull
    private String debitAccount;
    @NonNull
    private String creditAccount;
    @NonNull
    private String txnCcy;
    @NonNull
    private float amount;
    private String txnReference;
    private String status;
    private Date timestamp;

}
