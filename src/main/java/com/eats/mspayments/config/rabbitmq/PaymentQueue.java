package com.eats.mspayments.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentQueue {


    /* Payment done queue */
    @Bean
    public Queue paymentDoneQueue() {
        return QueueBuilder
                .nonDurable("payments.payment-done")
                .build();
    }
}
