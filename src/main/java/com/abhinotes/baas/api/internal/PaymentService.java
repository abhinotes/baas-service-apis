package com.abhinotes.baas.api.internal;

import com.abhinotes.baas.api.model.Payment;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@RestController
@Log
public class PaymentService {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path = "/payments/debit")
    @ResponseBody
    private Payment doPayment(@RequestBody Payment payment) {
        log.info(String.format("Received debit payment request : ", payment.toString()));
        //String accountAPIURL = String.format("http://localhost:8080/baas/api/accounts/valid/%s", payment.getDebitAccount());
        String accountAPIURL = String.format("accounts/valid/%s", payment.getDebitAccount());
        log.info(accountAPIURL);
        log.info(restTemplate
                .getForObject(accountAPIURL,Boolean.class).toString());

        payment.setTimestamp(new Date());
        payment.setTxnReference(UUID.randomUUID().toString());
        payment.setStatus("Success");

        return payment;
    }

}
