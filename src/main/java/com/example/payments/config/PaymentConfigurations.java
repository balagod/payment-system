package com.example.payments.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "payment-config")
public class PaymentConfigurations {
    private Map<String, PaymentTypeConfig> types = new HashMap<>();

    public Map<String, PaymentTypeConfig> getTypes() {
        return types;
    }

    public void setTypes(Map<String, PaymentTypeConfig> types) {
        this.types = types;
    }

    public static class PaymentTypeConfig {
        private List<String> mandatoryFields;
        private List<String> dataCollectors;
        private List<String> rules;

        public List<String> getMandatoryFields() {
            return mandatoryFields;
        }

        public void setMandatoryFields(List<String> mandatoryFields) {
            this.mandatoryFields = mandatoryFields;
        }

        public List<String> getDataCollectors() {
            return dataCollectors;
        }

        public void setDataCollectors(List<String> dataCollectors) {
            this.dataCollectors = dataCollectors;
        }

        public List<String> getRules() {
            return rules;
        }

        public void setRules(List<String> rules) {
            this.rules = rules;
        }
    }
}