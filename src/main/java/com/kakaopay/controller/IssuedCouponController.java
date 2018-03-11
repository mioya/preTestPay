package com.kakaopay.controller;

import com.kakaopay.exception.DupulicateCouponException;
import com.kakaopay.exception.DupulicateEmailException;
import com.kakaopay.exception.InvaildEmailFormatException;
import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.service.IssuedCouponService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@RestController
@CommonsLog
public class IssuedCouponController {

    IssuedCouponService issuedCouponService;

    private static final String EMAIL_PATTEN = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";

    @Autowired
    public IssuedCouponController(IssuedCouponService issuedCouponService) {
        this.issuedCouponService = issuedCouponService;
    }

    @GetMapping(value = "/issued-coupons")
    public Iterable<IssuedCoupon> list(){
        Iterable<IssuedCoupon> issuedCouponList = issuedCouponService.findAll();
        return issuedCouponList;
    }

    @PostMapping(value = "/issued-coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody IssuedCoupon issuedCoupon){
        checkInputVaild(issuedCoupon);
        issuedCouponService.saveByEmail(issuedCoupon);
    }

    private void checkInputVaild(IssuedCoupon issuedCoupon) {
        if(!Pattern.matches(EMAIL_PATTEN, issuedCoupon.getEmail())){
            throw new InvaildEmailFormatException();
        }
    }

    @ExceptionHandler(value = {DupulicateCouponException.class})
    public HttpStatus couponExceptionHandler(DupulicateCouponException e){
        return e.getExceptionCode(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {DupulicateEmailException.class})
    public HttpStatus emailExceptionHandler(DupulicateEmailException e){
        return e.getExceptionCode(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvaildEmailFormatException.class})
    public HttpStatus invaildEmailExceptionHandler(InvaildEmailFormatException e){
        return e.getExceptionCode(HttpStatus.SERVICE_UNAVAILABLE);
    }
}
