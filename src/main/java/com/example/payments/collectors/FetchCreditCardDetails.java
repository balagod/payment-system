package com.example.payments.collectors;

import org.springframework.stereotype.Component;

import com.example.payments.dtos.PaymentContext;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component("FetchCreditCardDetails")
public class FetchCreditCardDetails implements DataCollector {
   
     @Override
    public String getName() {
        return "FetchCreditCardDetails";
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
                "VISA", "4111111111111111",
                "VISA", "4012888888881881",
                "AMEX", "378282246310005",
                "JCB","3530111333300000",
                "MasterCard", "5555555555554444"
            );
        });
    }
}