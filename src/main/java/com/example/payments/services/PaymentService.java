package com.example.payments.services;

import com.example.payments.dtos.PaymentRequest;
import com.example.payments.dtos.PaymentResponse;
import java.util.concurrent.CompletableFuture;

public interface PaymentService {
    CompletableFuture<PaymentResponse> processPayment(PaymentRequest request);
}