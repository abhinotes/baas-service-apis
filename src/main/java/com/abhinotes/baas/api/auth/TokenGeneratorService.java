package com.abhinotes.baas.api.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Dummy Service to generate Tokens
 */
@RestController
public class TokenGeneratorService {

    @GetMapping(path = "/auth/token")
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
