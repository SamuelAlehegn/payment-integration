package com.payment.payment.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private String name;
    private String email;
    private Double amount;
    private Double quantity;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String productName;

    public PaymentDTO() {
    }

    public PaymentDTO(String name, String email, Double amount, Double quantity, String currency, String method, String intent, String description, String productName) {
        this.name = name;
        this.email = email;
        this.amount = amount;
        this.quantity = quantity;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
        this.productName = productName;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
  
  public Double getQuantity() {
    return quantity;
  }
  
  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }
  
}
