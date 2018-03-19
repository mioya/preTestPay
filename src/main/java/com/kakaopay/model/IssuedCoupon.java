package com.kakaopay.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mio on 2018. 3. 9..
 */
@Entity
@Table(name = "issued_coupon", indexes = { @Index(name = "coupon_number", columnList = "coupon_number")},
        uniqueConstraints = @UniqueConstraint(name = "id", columnNames = { "email" }))
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class IssuedCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "coupon_number",length = 20, nullable = false)
    private String couponNumber;

    @Column(name = "created_dt", updatable = false)
    private LocalDateTime createdDt;

}
