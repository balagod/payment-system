package com.example.payments.rules;

import com.example.payments.dtos.PaymentContext;

public interface PaymentRule {
    String getName();
    boolean validate(PaymentContext context);
    String getErrorMessage();
}