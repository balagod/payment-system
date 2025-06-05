package com.example.payments.rules;

import java.util.Map;

public interface Rule {
    Object evaluate(Map<String, String> input, Map<String, Object> collectorResults);
}