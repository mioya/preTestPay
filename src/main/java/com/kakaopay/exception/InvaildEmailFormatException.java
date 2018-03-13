package com.kakaopay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mio on 2018. 3. 10..
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvaildEmailFormatException extends RuntimeException{
}
