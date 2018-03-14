package com.kakaopay.util;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by mio on 2018. 3. 10..
 */
@CommonsLog
public class CouponGeneraterTest {

    @Test
    public void testCouponDupulicate(){
        int testCount = 10;

        Set<String> couponeGenerateList = new TreeSet<>();
        for(int i =0;i<testCount;i++){
            String coupon = CouponGenerater.generate();
            //log.info(coupon);
            couponeGenerateList.add(coupon);
        }
        log.info(couponeGenerateList.size());
        assertEquals(testCount,couponeGenerateList.size());
    }


}