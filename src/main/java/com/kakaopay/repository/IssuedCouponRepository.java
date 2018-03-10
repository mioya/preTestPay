package com.kakaopay.repository;

import com.kakaopay.model.IssuedCoupon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IssuedCouponRepository extends PagingAndSortingRepository<IssuedCoupon,String> {
    boolean existsByEmailOrCouponNumber(String email, String couponNumber);
}

