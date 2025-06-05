package com.example.payments.dtos;

import java.util.List;
import java.util.Map;

public class PaymentResponse {
    private boolean success;
    private List<String> errors;
    private Map<String, Object> data;

    private PaymentResponse(boolean success, List<String> errors, Map<String, Object> data) {
        this.success = success;
        this.errors = errors;
        this.data = data;
    }

    public static PaymentResponse success(Map<String, Object> data) {
        return new PaymentResponse(true, List.of(), data);
    }

    public static PaymentResponse failure(List<String> errors) {
        return new PaymentResponse(false, errors, Map.of());
    }

    // Getters
    public boolean isSuccess() { return success; }
    public List<String> getErrors() { return errors; }
    public Map<String, Object> getData() { return data; }
}