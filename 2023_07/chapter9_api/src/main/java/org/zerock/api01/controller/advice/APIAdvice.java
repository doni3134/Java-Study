package org.zerock.api01.controller.advice;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zerock.api01.controller.APIUserController;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class APIAdvice {

    @ExceptionHandler({APIUserController.APIUserNotFoundException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> findUserError(APIUserController.APIUserNotFoundException e) {

        log.error(e);
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("TIME", ""+System.currentTimeMillis());
        errorMap.put("RESULT",  "USER ACCOUNT NOT FOUND");

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorMap);
    }

    @ExceptionHandler({APIUserController.BadRefreshRequestException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> refrehRequestError(APIUserController.BadRefreshRequestException e) {

        log.error(e);
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("TIME", ""+System.currentTimeMillis());
        errorMap.put("RESULT",  "check grant_type & refresh_token parameter");

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorMap);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String, String>> expiredJWTError(ExpiredJwtException e) {

        log.error(e);
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("TIME", ""+System.currentTimeMillis());
        errorMap.put("RESULT",  "EXPIRED REFRESH TOKEN");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }
}
