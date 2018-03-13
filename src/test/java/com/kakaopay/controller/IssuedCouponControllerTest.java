package com.kakaopay.controller;

import com.kakaopay.model.IssuedCoupon;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;


/**
 * Created by mio on 2018. 3. 10..
 */
public class IssuedCouponControllerTest {

    private static final String COUPON_URL
            = "http://localhost:8080/issued-coupons";

    @Test
    public void testGetListIssuedCoupon() {
        Response response = RestAssured.get(COUPON_URL);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void testIssueCouponByEmail() {
        IssuedCoupon issuedCoupon = new IssuedCoupon();
        issuedCoupon.setEmail("kw23523@naver.com");

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(issuedCoupon)
                .post(COUPON_URL);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void testIssueCouponByWrongFormatEmail() {
        IssuedCoupon issuedCoupon = new IssuedCoupon();
        issuedCoupon.setEmail("kw23523m");

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(issuedCoupon)
                .post(COUPON_URL);

        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatusCode());
    }
}