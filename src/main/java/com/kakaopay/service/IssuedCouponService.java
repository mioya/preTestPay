package com.kakaopay.service;

import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.repository.IssuedCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuedCouponService {

    IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
    }

    public String save(IssuedCoupon issuedCoupon) {
        //TODO
        return null;
    }
}
