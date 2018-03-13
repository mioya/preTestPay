package com.kakaopay.repository;

import com.kakaopay.CouponApplicationTests;
import com.kakaopay.model.IssuedCoupon;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by mio on 2018. 3. 10..
 */
@CommonsLog
public class IssuedCouponRepositoryTest extends CouponApplicationTests{

    @Autowired
    private IssuedCouponRepository issuedCouponRepository;

    private static String TEST_COUPON_NUMBER_0 = "82Wp-p7Dz-E0Ws-NxaC";
    private static String TEST_EMAIL_0 = "kwchoi@naver.com";
    private static String TEST_EMAIL_1 = "kwc@naver.com";

    @Before
    public void createSampleIssuedCoupon() {
        IssuedCoupon issuedCoupon = new IssuedCoupon();
        issuedCoupon.setCouponNumber(TEST_COUPON_NUMBER_0);
        issuedCoupon.setCreatedDt(new Date());
        issuedCoupon.setEmail(TEST_EMAIL_0);
        issuedCouponRepository.save(issuedCoupon);
    }

    @After
    public void remove(){
        issuedCouponRepository.deleteAll();
    }

    @Test
    public void testFindByEmailAndCouponNumber(){
        boolean isExistsCouponOrEmail = issuedCouponRepository.existsByEmail(TEST_EMAIL_0);
        log.info(isExistsCouponOrEmail);
        assertEquals(isExistsCouponOrEmail, true);
    }
    @Test
    public void testNotFindByEmailAndCouponNumber(){
        boolean isExistsCouponOrEmail = issuedCouponRepository.existsByEmail(TEST_EMAIL_1);
        log.info(isExistsCouponOrEmail);
        assertEquals(isExistsCouponOrEmail, false);
    }

    @Test
    public void testFindByNotIssuedCouponAndEmail(){
        boolean isExistsCouponOrEmail = issuedCouponRepository.existsByCouponNumber(TEST_COUPON_NUMBER_0);
        assertEquals(isExistsCouponOrEmail, true);
    }

}