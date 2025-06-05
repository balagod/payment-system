package com.example.payments.rules;

import com.example.payments.dtos.PaymentContext;
import org.springframework.stereotype.Component;

@Component
public class DailyLimitRule implements PaymentRule {
    @Override
    public String getName() {
        return "DailyLimit";
    }

    @Override
    public boolean validate(PaymentContext context) {
        Double remainingDailyLimit = (Double) context.getData().get("remainingDailyLimit");
        return remainingDailyLimit != null && 
               remainingDailyLimit >= context.getRequest().getAmount().doubleValue();
    }

    @Override
    public String getErrorMessage() {
        return "Transaction exceeds daily limit";
    }
}