## Key Design Patterns Illustrated:
### Strategy Pattern:
DataCollector and PaymentRule interfaces with multiple implementations
Allows interchangeable algorithms for data collection and validation
### Factory Pattern:
PaymentServiceImpl acts as a factory creating appropriate collectors/rules
Automatic discovery via Spring dependency injection
### Chain of Responsibility:
Sequential processing of validation rules
Each rule can approve or reject the payment
### Builder Pattern:
PaymentRequest.Builder for constructing complex payment requests
Fluent interface for easy object creation
### Observer Pattern:
PaymentResponse collects results from multiple operations
Centralized error handling
### Facade Pattern:
PaymentController provides simplified interface to complex payment processing
### Configuration Pattern:
PaymentConfigurations centralizes all payment type configurations
The diagram shows how these patterns work together to create a flexible, maintainable payment processing system where:
New payment types can be added via configuration
New data collectors and rules can be added without modifying existing code
The processing flow is clearly separated into distinct components
Parallel processing is handled through CompletableFuture

## UML:

```mermaid
classDiagram
    %% Configuration Classes
    class PaymentConfigurations {
        +Map<String, PaymentTypeConfig> types
    }
    
    class PaymentTypeConfig {
        +List~String~ mandatoryFields
        +List~String~ dataCollectors
        +List~String~ rules
    }
    
    %% Strategy Pattern
    class DataCollector {
        <<interface>>
        +String getName()
        +CompletableFuture~Map~ collect(PaymentContext context)
    }
    
    class FetchAccountDetails {
        +String getName()
        +CompletableFuture~Map~ collect(PaymentContext context)
    }
    
    class PaymentRule {
        <<interface>>
        +String getName()
        +boolean validate(PaymentContext context)
        +String getErrorMessage()
    }
    
    class AccountBalanceRule {
        +String getName()
        +boolean validate(PaymentContext context)
        +String getErrorMessage()
    }
    
    DataCollector <|.. FetchAccountDetails
    PaymentRule <|.. AccountBalanceRule
    
    %% Factory Pattern
    class PaymentServiceImpl {
        -Map~String,DataCollector~ dataCollectors
        -Map~String,PaymentRule~ paymentRules
        +processPayment(PaymentRequest request) CompletableFuture~PaymentResponse~
    }
    
    PaymentServiceImpl --> DataCollector: "creates"
    PaymentServiceImpl --> PaymentRule: "creates"
    
    %% Chain of Responsibility
    class PaymentContext {
        -PaymentRequest request
        -Map~String,Object~ collectedData
        -List~String~ errors
        +addData()
        +addError()
    }
    
    PaymentServiceImpl --> PaymentContext: "manages"
    
    %% Builder Pattern
    class PaymentRequest {
        -String paymentType
        -String name
        -String accountNo
        -BigDecimal amount
        +static Builder
    }
    
    class PaymentRequest.Builder {
        -PaymentRequest request
        +withPaymentType()
        +withName()
        +withAmount()
        +build()
    }
    
    PaymentRequest --> PaymentRequest.Builder: "creates"
    
    %% Observer Pattern
    class PaymentResponse {
        -boolean success
        -List~String~ errors
        -Map~String,Object~ data
        +success()
        +failure()
    }
    
    PaymentServiceImpl --> PaymentResponse: "returns"
    
    %% Facade Pattern
    class PaymentController {
        -PaymentService paymentService
        +processPayment()
    }
    
    PaymentController --> PaymentService: "uses"
    
    %% Notes for Design Patterns
    note for PaymentConfigurations "Configuration Pattern\nCentralized configuration management"
    note for DataCollector "Strategy Pattern\nDifferent implementations can be swapped"
    note for PaymentServiceImpl "Factory Pattern\nCreates appropriate collectors/rules\n\nChain of Responsibility\nProcesses rules sequentially"
    note for PaymentRequest.Builder "Builder Pattern\nConstructs complex objects step by step"
    note for PaymentContext "Context Object\nShared state between strategies"
    note for PaymentController "Facade Pattern\nSimplified interface to complex system"
    ```



