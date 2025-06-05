package com.example.payments.collectors;

import com.example.payments.dtos.PaymentContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class FetchAccountDetails implements DataCollector {
    @Override
    public String getName() {
        return "FetchAccountDetails";
    }

    @Override
    public CompletableFuture<Map<String, Object>> collect(PaymentContext context) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate API call
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return Map.of(
                "balance", 1000.00,
                "currency", "USD",
                "status", "ACTIVE"
            );
        });
    }
}