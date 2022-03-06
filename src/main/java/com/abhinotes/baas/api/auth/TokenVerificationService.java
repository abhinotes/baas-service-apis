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
        if (requestData != null && requestData.contains("InvalidToken")) {
            log.info("Token verification failed!!");
            return false;
        }
        log.info("Token verified Successfully!!!");
        return true;
    }

}
