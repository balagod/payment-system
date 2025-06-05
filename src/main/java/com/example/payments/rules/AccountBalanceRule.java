package com.example.payments.rules;

import com.example.payments.dtos.PaymentContext;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceRule implements PaymentRule {
    @Override
    public String getName() {
        return "AccountBalance";
    }

    @Override
    public boolean validate(PaymentContext context) {
        Double balance = (Double) context.getData().get("balance");
        return balance != null && balance >= context.getRequest().getAmount().doubleValue();
    }

    @Override
    public String getErrorMessage() {
        return "Insufficient account balance";
    }
}