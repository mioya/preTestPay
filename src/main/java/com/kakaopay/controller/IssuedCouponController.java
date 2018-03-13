package com.kakaopay.controller;

import com.kakaopay.exception.InvaildEmailFormatException;
import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.service.IssuedCouponService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<IssuedCoupon> list(@PageableDefault(size = 3) Pageable pageable){
        return issuedCouponService.findAll(pageable);
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

}
