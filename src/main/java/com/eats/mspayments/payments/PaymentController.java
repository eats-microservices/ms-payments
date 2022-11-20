package com.eats.mspayments.payments;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public PaymentController(PaymentRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/pay/{orderId}/card")
    public ResponseEntity<?> payWithCard(@PathVariable Long orderId) {
        return null;
    }


    @PostMapping("/pay/{orderId}/pix")
    public ResponseEntity<?> payWithPix(@PathVariable Long orderId) {
        return null;
    }


    @PostMapping("/pay/{orderId}/cash")
    public ResponseEntity<?> payWithCash(@PathVariable Long orderId) {
        return null;
    }
}
