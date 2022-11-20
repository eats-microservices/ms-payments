package com.eats.mspayments.payments;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long orderId;
    private Instant receivedAt;
    private Boolean isPayed;
    private Instant payedAt;
    private PaymentType type;

    @Deprecated
    public Payment() {}

    public Payment(Long userId, Long orderId, Instant receivedAt, Boolean isPayed, Instant payedAt, PaymentType type) {
        this.userId = userId;
        this.orderId = orderId;
        this.receivedAt = receivedAt;
        this.isPayed = isPayed;
        this.payedAt = payedAt;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public PaymentType getType() {
        return type;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

    public Instant getPayedAt() {
        return payedAt;
    }
}
