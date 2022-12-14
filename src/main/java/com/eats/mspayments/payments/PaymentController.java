package com.eats.mspayments.payments;

import com.eats.mspayments.exceptions.ResourceNotFoundException;
import com.eats.mspayments.payments.dtos.PayWithCardRequest;
import com.eats.mspayments.payments.dtos.PayWithPix;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

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
    public ResponseEntity<?> payWithCard(@PathVariable Long orderId, @RequestBody PayWithCardRequest request) {
        Optional<Payment> possiblePayment = repository.findByOrderId(orderId);

        if (possiblePayment.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with this order id: " + orderId);
        }

        Payment payment = possiblePayment.get();
        Payment updatedPayment = new Payment(
                payment.getId(),
                payment.getUserId(),
                payment.getOrderId(),
                payment.getReceivedAt(),
                true,
                Instant.now(),
                request.getCredit() ? PaymentType.CREDIT_CARD : PaymentType.DEBIT_CARD);

        repository.save(updatedPayment);

        PaymentDoneDto paymentDoneDto = new PaymentDoneDto(updatedPayment.getOrderId(), updatedPayment.getUserId());
        rabbitTemplate.convertAndSend("payments.ex", "payments.payment-done", paymentDoneDto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/pay/{orderId}/pix")
    public ResponseEntity<?> payWithPix(@PathVariable Long orderId, @RequestBody PayWithPix request) {
        Optional<Payment> possiblePayment = repository.findByOrderId(orderId);

        if (possiblePayment.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with this order id: " + orderId);
        }

        Payment payment = possiblePayment.get();
        Payment updatedPayment = new Payment(
                payment.getId(),
                payment.getUserId(),
                payment.getOrderId(),
                payment.getReceivedAt(),
                true,
                Instant.now(),
                PaymentType.PIX);

        repository.save(updatedPayment);

        PaymentDoneDto paymentDoneDto = new PaymentDoneDto(updatedPayment.getOrderId(), updatedPayment.getUserId());
        rabbitTemplate.convertAndSend("payments.ex", "payments.payment-done", paymentDoneDto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/pay/{orderId}/cash")
    public ResponseEntity<?> payWithCash(@PathVariable Long orderId) {
        Optional<Payment> possiblePayment = repository.findByOrderId(orderId);

        if (possiblePayment.isEmpty()) {
            throw new ResourceNotFoundException("Payment not found with this order id: " + orderId);
        }

        Payment payment = possiblePayment.get();
        Payment updatedPayment = new Payment(
                payment.getId(),
                payment.getUserId(),
                payment.getOrderId(),
                payment.getReceivedAt(),
                true,
                Instant.now(),
                PaymentType.CASH);

        repository.save(updatedPayment);

        PaymentDoneDto paymentDoneDto = new PaymentDoneDto(updatedPayment.getOrderId(), updatedPayment.getUserId());
        rabbitTemplate.convertAndSend("payments.ex", "payments.payment-done", paymentDoneDto);
        return ResponseEntity.ok().build();
    }


    static class PaymentDoneDto {
        private final Long id;
        private final Long userId;

        public PaymentDoneDto(Long id, Long userId) {
            this.id = id;
            this.userId = userId;
        }

        public Long getId() {
            return id;
        }

        public Long getUserId() {
            return userId;
        }
    }
}
