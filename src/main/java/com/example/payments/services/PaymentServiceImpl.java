package com.example.payments.services;

import com.example.payments.config.PaymentConfigurations;
import com.example.payments.collectors.DataCollector;
import com.example.payments.dtos.*;
import com.example.payments.rules.PaymentRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentConfigurations configurations;
    private final Map<String, DataCollector> dataCollectors;
    private final Map<String, PaymentRule> paymentRules;
    private final Executor asyncExecutor;

    @Autowired
    public PaymentServiceImpl(PaymentConfigurations configurations,
                            List<DataCollector> collectors,
                            List<PaymentRule> rules,
                            @Qualifier("paymentTaskExecutor") Executor asyncExecutor) {
        this.configurations = configurations;
        this.dataCollectors = collectors.stream()
            .collect(Collectors.toMap(DataCollector::getName, Function.identity()));
        this.paymentRules = rules.stream()
            .collect(Collectors.toMap(PaymentRule::getName, Function.identity()));
        this.asyncExecutor = asyncExecutor;
    }

    @Override
    public CompletableFuture<PaymentResponse> processPayment(PaymentRequest request) {
        PaymentConfigurations.PaymentTypeConfig config = configurations.getTypes().get(request.getPaymentType());
        if (config == null) {
            return CompletableFuture.completedFuture(
                PaymentResponse.failure(List.of("Invalid payment type")));
        }

        PaymentContext context = new PaymentContext(request);

        // Parallel data collection
        List<CompletableFuture<Void>> collectionFutures = config.getDataCollectors().stream()
            .map(dataCollectors::get)
            .filter(Objects::nonNull)
            .map(collector -> collector.collect(context)
                .thenAccept(data -> context.addData(collector.getName(), data))
                .exceptionally(ex -> {
                    context.addError("Failed to collect data: " + ex.getMessage());
                    return null;
                }))
            .collect(Collectors.toList());

        return CompletableFuture.allOf(collectionFutures.toArray(new CompletableFuture[0]))
            .thenComposeAsync(v -> {
                if (!context.getErrors().isEmpty()) {
                    return CompletableFuture.completedFuture(
                        PaymentResponse.failure(context.getErrors()));
                }

                // Validate rules
                List<String> ruleErrors = config.getRules().stream()
                    .map(paymentRules::get)
                    .filter(Objects::nonNull)
                    .filter(rule -> !rule.validate(context))
                    .map(PaymentRule::getErrorMessage)
                    .collect(Collectors.toList());

                if (!ruleErrors.isEmpty()) {
                    return CompletableFuture.completedFuture(
                        PaymentResponse.failure(ruleErrors));
                }

                // Process payment
                return processPaymentCore(context);
            }, asyncExecutor);
    }

    private CompletableFuture<PaymentResponse> processPaymentCore(PaymentContext context) {
        // In a real implementation, this would process the actual payment
        return CompletableFuture.completedFuture(
            PaymentResponse.success(Map.of(
                "transactionId", java.util.UUID.randomUUID().toString(),
                "status", "COMPLETED",
                "timestamp", java.time.Instant.now().toString()
            )));
    }
}