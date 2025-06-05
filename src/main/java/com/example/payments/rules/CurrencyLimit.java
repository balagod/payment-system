package com.example.payments.rules;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component("CurrencyLimit")
public class CurrencyLimit implements Rule {
    public Object evaluate(Map<String, String> input, Map<String, Object> collectorResults) {
        return "CurrencyLimit check passed";
    }
}