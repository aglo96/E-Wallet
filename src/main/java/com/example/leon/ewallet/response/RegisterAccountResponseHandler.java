package com.example.leon.ewallet.response;

import com.example.leon.ewallet.entity.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RegisterAccountResponseHandler {
    public static ResponseEntity<Object> generateResponse(HttpStatus status, Account account) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (status.is2xxSuccessful()) {
            map.put("success", true);
            map.put("balance", account.getBalance());
        } else {
            map.put("success", false);
        }

        return new ResponseEntity<Object>(map, status);

    }
}
