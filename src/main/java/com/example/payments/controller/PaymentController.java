package com.example.payments.controllers;

import com.example.payments.dtos.PaymentRequest;
import com.example.payments.dtos.PaymentResponse;
import com.example.payments.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<PaymentResponse>> processPayment(
            @RequestBody PaymentRequest request) {
        return paymentService.processPayment(request)
            .thenApply(response -> ResponseEntity.status(
                response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(response));
    }
}