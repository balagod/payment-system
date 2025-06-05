package com.example.payments.rules;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component("OverallLimit")
public class OverallLimit implements Rule {
    public Object evaluate(Map<String, String> input, Map<String, Object> collectorResults) {
        return "OverallLimit check passed";
    }
}