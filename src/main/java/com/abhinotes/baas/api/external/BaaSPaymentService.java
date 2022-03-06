package com.abhinotes.baas.api.external;

import com.abhinotes.baas.api.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BaaSPaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${baas.urls.base}")
    String baseUrl;

    @PostMapping(path = "baas/payment")
    private Payment callPaymentWorkFlow(@RequestBody Payment paymentRequest) {
        String tokenUrl = String.format("%sauth/token",baseUrl);
        String bankPaymentUrl = String.format("%spayments/debit",baseUrl);
        String myToken = restTemplate
                .getForObject(tokenUrl,String.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("securityToken", myToken);
        HttpEntity<Payment> paymentPostRequest = new HttpEntity<>(paymentRequest, headers);
        return restTemplate.postForObject(bankPaymentUrl, paymentPostRequest, Payment.class);
    }
}
