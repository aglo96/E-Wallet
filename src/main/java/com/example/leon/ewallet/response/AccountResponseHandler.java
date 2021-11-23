package com.example.leon.ewallet.response;

import com.example.leon.ewallet.entity.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class AccountResponseHandler {
    public static ResponseEntity<Object> generateSuccessResponse(HttpStatus status, Account account) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (status.is2xxSuccessful()) {
            map.put("success", true);
            map.put("balance", account.getBalance());
        } else {
            map.put("success", false);
        }

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateErrorResponse(String reason) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("error", reason);
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

}
