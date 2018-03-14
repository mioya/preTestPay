package com.kakaopay.service;

import com.kakaopay.exception.DupulicateCouponException;
import com.kakaopay.exception.DupulicateEmailException;
import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.repository.IssuedCouponRepository;
import com.kakaopay.util.CouponGenerater;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@CommonsLog
public class IssuedCouponService {

    IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
    }

    @Transactional
    public synchronized IssuedCoupon saveByEmail(IssuedCoupon issuedCoupon) {

        if (issuedCouponRepository.existsByEmail(issuedCoupon.getEmail())) {
            throw new DupulicateEmailException(String.format("email already exists-->%s", issuedCoupon.getEmail()));
        }
        issuedCoupon.setCreatedDt(LocalDateTime.now());

        String generatedCoupon = CouponGenerater.generate();
        if (issuedCouponRepository.existsByCouponNumber(generatedCoupon)) {
            throw new DupulicateCouponException(String.format("coupon already generate-->%s", generatedCoupon));
        }
        issuedCoupon.setCouponNumber(generatedCoupon);

        issuedCoupon = issuedCouponRepository.save(issuedCoupon);
        return issuedCoupon;
    }

    public Page<IssuedCoupon> findAll(Pageable pageable) {
        return issuedCouponRepository.findAll(pageable);
    }
}
