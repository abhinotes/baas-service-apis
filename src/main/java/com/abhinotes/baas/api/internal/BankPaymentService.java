package com.abhinotes.baas.api.internal;

import com.abhinotes.baas.api.model.Payment;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@Log
public class BankPaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${baas.urls.base}")
    String baseUrl;

    @PostMapping(path = "/payments/debit")
    private Payment doPayment(@RequestBody Payment payment, HttpServletRequest request, HttpServletResponse response) throws IOException {



        String securityToken =  request.getHeader("securityToken");
        String tokenValidationUrl = String.format("%sauth/verify",baseUrl);
        if (securityToken == null || !restTemplate
                .postForObject(tokenValidationUrl,securityToken, Boolean.class)) {
            if (securityToken == null) {
                log.info("Unauthorized, Please provide a valid security token in http header with key \"securityToken\" !!!");
            }
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        log.info(String.format("Received debit payment request : %s", payment.toString()));
        String accountAPIURL = String.format("%saccounts/valid/%s",baseUrl, payment.getDebitAccount());
        log.info(accountAPIURL);

        boolean isValidAccount = restTemplate
                .getForObject(accountAPIURL,Boolean.class);

        if(isValidAccount && payment.getAmount() > 0 ) {
            payment.setStatus("Success");
        } else {
            payment.setStatus("Failed, Invalid Account/Amount!!");
        }

        payment.setTimestamp(new Date());
        payment.setTxnReference(UUID.randomUUID().toString());

        return payment;
    }

}
