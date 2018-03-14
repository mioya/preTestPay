package com.kakaopay.service;

import com.kakaopay.CouponApplicationTests;
import com.kakaopay.model.IssuedCoupon;
import com.kakaopay.repository.IssuedCouponRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by mio on 2018. 3. 10..
 */
@CommonsLog
public class IssuedCouponServiceTest extends CouponApplicationTests {

    @Autowired
    IssuedCouponService issuedCouponService;

    @Autowired
    IssuedCouponRepository issuedCouponRepository;

    private static int TEST_COUNT = 5;
    private static int THREAD_COUNT = 10;

    private static List<IssuedCoupon> couponList = new ArrayList<>();

    @Test
    public void testFindByEmailAndCouponNumber() throws InterruptedException {

        Runnable r = new TestGenerate();
        ArrayList<Thread> threadList = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread test = new Thread(r);
            test.start();
            threadList.add(test);
        }

        for (Thread t : threadList) {
            t.join();
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        log.info("time-->" + estimatedTime + " ms");
        //log.info(couponList.toString());
        assertEquals((TEST_COUNT*THREAD_COUNT),issuedCouponRepository.count());
    }

    class TestGenerate implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < TEST_COUNT; i++) {
                IssuedCoupon issuedCoupon = new IssuedCoupon();
                issuedCoupon.setEmail(createEmail());
                issuedCoupon = issuedCouponService.saveByEmail(issuedCoupon);
                //log.info(issuedCoupon);
                couponList.add(issuedCoupon);
            }
        }
    }

    private String createEmail() {
        byte[] array = new byte[20];
        Random random = new Random();
        random.nextBytes(array);
        return new String(array);
    }

}