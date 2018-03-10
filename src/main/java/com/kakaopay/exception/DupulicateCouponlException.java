package com.kakaopay.exception;

/**
 * Created by mio on 2018. 3. 10..
 */
public class DupulicateCouponlException extends RuntimeException{
    public String getExceptionCode(String message) {
        return "901";
    }
}
