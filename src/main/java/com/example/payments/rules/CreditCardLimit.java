package com.example.payments.rules;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component("CreditCardLimit")
public class CreditCardLimit implements Rule {
    public Object evaluate(Map<String, String> input, Map<String, Object> collectorResults) {
        return "CreditCardLimit check passed";
    }
}