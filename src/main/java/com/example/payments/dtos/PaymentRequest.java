package com.example.payments.dtos;

import java.math.BigDecimal;

public class PaymentRequest {
    private String paymentType;
    private String name;
    private String accountNo;
    private String payeeName;
    private String payeeAccountNo;
    private String payeeBankCode;
    private BigDecimal amount;

    // Builder pattern implementation
    public static class Builder {
        private final PaymentRequest request = new PaymentRequest();

        public Builder withPaymentType(String paymentType) {
            request.paymentType = paymentType;
            return this;
        }

        public Builder withName(String name) {
            request.name = name;
            return this;
        }

        public Builder withAccountNo(String accountNo) {
            request.accountNo = accountNo;
            return this;
        }

        public Builder withPayeeName(String payeeName) {
            request.payeeName = payeeName;
            return this;
        }

        public Builder withPayeeAccountNo(String payeeAccountNo) {
            request.payeeAccountNo = payeeAccountNo;
            return this;
        }

        public Builder withPayeeBankCode(String payeeBankCode) {
            request.payeeBankCode = payeeBankCode;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            request.amount = amount;
            return this;
        }

        public PaymentRequest build() {
            return request;
        }
    }

    // Getters
    public String getPaymentType() { return paymentType; }
    public String getName() { return name; }
    public String getAccountNo() { return accountNo; }
    public String getPayeeName() { return payeeName; }
    public String getPayeeAccountNo() { return payeeAccountNo; }
    public String getPayeeBankCode() { return payeeBankCode; }
    public BigDecimal getAmount() { return amount; }
}