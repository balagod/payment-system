package com.example.payments.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentContext {
    private final PaymentRequest request;
    private final Map<String, Object> collectedData = new ConcurrentHashMap<>();
    private final List<String> errors = new ArrayList<>();

    public PaymentContext(PaymentRequest request) {
        this.request = request;
    }

    public void addData(String key, Object value) {
        collectedData.put(key, value);
    }

    public void addError(String error) {
        errors.add(error);
    }

    // Getters
    public PaymentRequest getRequest() { return request; }
    public Map<String, Object> getData() { return collectedData; }
    public List<String> getErrors() { return errors; }
}