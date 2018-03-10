package com.kakaopay.controller;

import com.kakaopay.exception.DupulicateCouponException;
import com.kakaopay.exception.DupulicateEmailException;
import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.repository.IssuedCouponRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CommonsLog
public class IssuedCouponController {

    IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public IssuedCouponController(IssuedCouponRepository issuedCouponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
    }

    @GetMapping(value = "/issued-coupons")
    public Iterable<IssuedCoupon> list(){
        Iterable<IssuedCoupon> issuedCouponList = issuedCouponRepository.findAll();
        return issuedCouponList;
    }

    @PostMapping(value = "/issued-coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody IssuedCoupon issuedCoupon){
        issuedCouponRepository.save(issuedCoupon);
    }

    @ExceptionHandler(value = {DupulicateCouponException.class})
    public HttpStatus couponExceptionHandler(DupulicateCouponException e){
        return e.getExceptionCode(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {DupulicateEmailException.class})
    public HttpStatus emailExceptionHandler(DupulicateEmailException e){
        return e.getExceptionCode(HttpStatus.BAD_REQUEST);
    }

}
