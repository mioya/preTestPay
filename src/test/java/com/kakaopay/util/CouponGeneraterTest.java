package com.kakaopay.util;

import lombok.extern.apachecommons.CommonsLog;
import org.junit.Test;

import java.security.SecureRandom;
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
        //100만번 15초
        int testCount = 10;

        Set<String> couponeGenerateList = new TreeSet<>();
        for(int i =0;i<testCount;i++){
            couponeGenerateList.add(CouponGenerater.generate());
        }
        log.debug(couponeGenerateList.size());
        assertEquals(testCount,couponeGenerateList.size());
    }

}