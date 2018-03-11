package com.kakaopay.repository;

import com.kakaopay.model.IssuedCoupon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;


@Repository
public interface IssuedCouponRepository extends PagingAndSortingRepository<IssuedCoupon,Long> {
    boolean existsByCouponNumber(String couponNumber);

    boolean existsByEmail(String email);
}
