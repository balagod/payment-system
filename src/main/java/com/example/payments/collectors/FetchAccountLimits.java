package com.example.payments.collectors;

import com.example.payments.dtos.PaymentContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class FetchAccountLimits implements DataCollector {
    @Override
    public String getName() {
        return "FetchAccountLimits";
    }

    @Override
    public CompletableFuture<Map<String, Object>> collect(PaymentContext context) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate API call
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return Map.of(
                "dailyLimit", 5000.00,
                "transactionLimit", 2000.00,
                "remainingDailyLimit", 3500.00
            );
        });
    }
}