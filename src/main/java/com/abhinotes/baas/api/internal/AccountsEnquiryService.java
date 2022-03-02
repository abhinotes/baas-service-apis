package com.abhinotes.baas.api.internal;

import com.abhinotes.baas.api.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dummy Account Enquiry service
 */
@RestController
public class AccountsEnquiryService {

    private static final List<String> VALIDACCOUNTS = Arrays.asList("9876543210","9876543212","9876543214", "8876543210");

    @GetMapping(path = "/accounts/{id}")
    private Account isValidAccount(@PathVariable String id) {
        Account userAccount = Account.builder()
                .accountId(id)
                .fullName(String.format("Happy Account user %S", id))
                .balance(Float.valueOf("100000.56"))
                .phone("+919000000000")
                .active(true)
                .build();

        return userAccount;
    }

    @GetMapping(path = "/accounts/valid/{id}")
    private boolean getAccountDetails(@PathVariable String id) throws InterruptedException {
        if ( VALIDACCOUNTS.contains(id)) {

            /* On purpose associating one account with
                delayed response scenarios*/

            if ( "8876543210".equals(id)) {
                Thread.sleep(10000);
            }
            return true;
        }
        return VALIDACCOUNTS.contains(id);
    }

}
