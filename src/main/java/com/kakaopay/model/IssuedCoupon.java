package com.kakaopay.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * Created by mio on 2018. 3. 9..
 */
@Entity
@Table(name = "issued_coupon",
        uniqueConstraints = @UniqueConstraint(name = "id", columnNames = { "email", "coupon_number" }))
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class IssuedCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "coupon_number",length = 20)
    private String couponNumber;

    @Column(name = "created_dt", updatable = false)
    private Date createdDt;
}
