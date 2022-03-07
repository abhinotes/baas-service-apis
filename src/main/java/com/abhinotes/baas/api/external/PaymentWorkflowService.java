package com.abhinotes.baas.api.external;

import com.abhinotes.baas.api.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentWorkflowService {

    private static final String PAYMENT_WORKFLOW_API_URL =
            "https://workflowexecutions.googleapis.com/v1/" +
                    "projects/silver-binder-342712/" +
                    "locations/asia-southeast1/" +
                    "workflows/debit-payment-workflow-2/" +
                    "executions";

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path = "baas/workflows/payments/debit")
    private Payment callPaymentWorkFlow(@RequestBody Payment paymentRequest) {
        return restTemplate.postForObject(
                PAYMENT_WORKFLOW_API_URL,
                paymentRequest, Payment.class);
    }
}
