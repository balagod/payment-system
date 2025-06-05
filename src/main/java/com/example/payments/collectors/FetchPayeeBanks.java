package com.example.payments.collectors;

import org.springframework.stereotype.Component;

import com.example.payments.dtos.PaymentContext;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component("FetchPayeeBanks")
public class FetchPayeeBanks implements DataCollector {
    
     @Override
    public String getName() {
        return "FetchPayeeBanks";
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
                "ICICI", "ICICI",
                "HDFC", "HDFC",
                "IDFC", "IDFC",
                "SCB","SCB",
                "SBI", "SBI"
            );
        });
    }
}