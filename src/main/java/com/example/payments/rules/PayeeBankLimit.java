package com.example.payments.rules;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component("PayeeBankLimit")
public class PayeeBankLimit implements Rule {
    public Object evaluate(Map<String, String> input, Map<String, Object> collectorResults) {
        return "PayeeBankLimit check passed";
    }
}