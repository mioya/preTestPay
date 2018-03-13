package com.kakaopay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mio on 2018. 3. 10..
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DupulicateCouponException extends RuntimeException{

}
