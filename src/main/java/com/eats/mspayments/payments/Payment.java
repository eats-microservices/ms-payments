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
    private PaymentType type;
    private Instant receivedAt;
    private Instant payedAt;

    @Deprecated
    public Payment() {}

    public Payment(Long userId, Long orderId, PaymentType type, Instant receivedAt, Instant payedAt) {
        this.userId = userId;
        this.orderId = orderId;
        this.type = type;
        this.receivedAt = receivedAt;
        this.payedAt = payedAt;
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
