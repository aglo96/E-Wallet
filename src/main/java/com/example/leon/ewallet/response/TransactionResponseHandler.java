package com.example.leon.ewallet.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class TransactionResponseHandler {
    public static ResponseEntity<Object> generateSuccessResponse() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> generateErrorResponse(String reason) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("error", reason);
        map.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

}
