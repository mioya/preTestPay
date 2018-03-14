package com.kakaopay.util;


import lombok.extern.apachecommons.CommonsLog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by mio on 2018. 3. 10..
 */
@CommonsLog
public class CouponGenerater {

    final static char[] COUPON_NUMBER_LIST = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z' ,
            'A' , 'B' ,
            'C' , 'D' , 'E' , 'F' , 'G' , 'H' ,
            'I' , 'J' , 'K' , 'L' , 'M' , 'N' ,
            'O' , 'P' , 'Q' , 'R' , 'S' , 'T' ,
            'U' , 'V' , 'W' , 'X' , 'Y' , 'Z'
    };

    public static String generate() {
        char[] tempcoupon = new char[20];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for(int index = 0;index < tempcoupon.length-1;index++){
            if((index+1)%5==0){
                tempcoupon[index++]= '-';
            }
            tempcoupon[index] = COUPON_NUMBER_LIST[Math.abs(random.nextInt()%(COUPON_NUMBER_LIST.length-1))];
        }
        return String.valueOf(tempcoupon);
    }
}
