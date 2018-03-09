package com.kakaopay.controller;

import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.repository.IssuedCouponRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CommonsLog
public class IssuedCouponController {

    IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public IssuedCouponController(IssuedCouponRepository issuedCouponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
    }

    @RequestMapping(value = "/issued-coupons", method = RequestMethod.GET)
    public Iterable<IssuedCoupon> list(){
        Iterable<IssuedCoupon> issuedCouponList = issuedCouponRepository.findAll();
        return issuedCouponList;
    }

}
