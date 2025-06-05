package com.example.payments.collectors;

import com.example.payments.dtos.PaymentContext;
import java.util.concurrent.CompletableFuture;
import java.util.Map;

public interface DataCollector {
    String getName();
    CompletableFuture<Map<String, Object>> collect(PaymentContext context);
}