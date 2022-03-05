package com.abhinotes.baas.api.auth;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dummy Service to verify Tokens
 */
@RestController
@Log
public class TokenVerificationService {

    @PostMapping(path = "/auth/verify")
    private boolean verifyToken(@RequestBody String requestData) {
        log.info(String.format("Auth Request for token %s is successful", requestData));
        if (requestData != null && requestData.contains("InvalidToken")) {
            return false;
        }
        return true;
    }

}
