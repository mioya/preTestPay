package com.kakaopay.exception;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mio on 2018. 3. 10..
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
@CommonsLog
public class InvaildEmailFormatException extends RuntimeException{

    public InvaildEmailFormatException(String message) {
        log.warn(message);
    }
}
