package com.kakaopay.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by mio on 2018. 3. 10..
 */
public class DupulicateEmailException extends RuntimeException{
    public HttpStatus getExceptionCode(HttpStatus message) {
        return message;
    }
}
