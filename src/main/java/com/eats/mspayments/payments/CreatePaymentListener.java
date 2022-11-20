package com.eats.mspayments.payments;

import com.eats.mspayments.payments.dtos.NewOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Component
public class CreatePaymentListener {


    private final Logger logger = LoggerFactory.getLogger(CreatePaymentListener.class);
    private final PaymentRepository repository;

    public CreatePaymentListener(PaymentRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "orders.new-order-event")
    @Transactional
    public void receiveMessage(@Payload NewOrderDto dto) {
        Payment payment = new Payment(dto.getUserId(), dto.getId(), Instant.now(), false, null, null);
        payment = repository.save(payment);
        logger.info("Payment save with id " + payment.getId());
    }
}
