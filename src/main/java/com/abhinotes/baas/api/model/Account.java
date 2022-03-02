package com.abhinotes.baas.api.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Account {
    private String accountId;
    private String fullName ;
    private float balance ;
    private String phone;
    private boolean active;
}
