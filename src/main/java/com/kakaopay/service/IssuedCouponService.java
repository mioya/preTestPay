package com.kakaopay.service;

import com.kakaopay.exception.DupulicateCouponException;
import com.kakaopay.exception.DupulicateEmailException;
import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.repository.IssuedCouponRepository;
import com.kakaopay.util.CouponGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IssuedCouponService {

    IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
    }

    @Transactional
    public IssuedCoupon save(IssuedCoupon issuedCoupon) {

        if(issuedCouponRepository.existsByEmail(issuedCoupon.getEmail())){
            throw new DupulicateEmailException();
        }

        String generatedCoupon = CouponGenerater.generate();

        if(issuedCouponRepository.existsByCouponNumber(generatedCoupon)){
            throw new DupulicateCouponException();
        }

        issuedCoupon.setCouponNumber(generatedCoupon);
        return issuedCouponRepository.save(issuedCoupon);
    }
}
