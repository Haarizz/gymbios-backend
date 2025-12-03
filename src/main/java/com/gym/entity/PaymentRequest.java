// src/main/java/com/yourcompany/gymbios/billing/PaymentRequest.java
package com.gym.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequest {

    private BigDecimal amount;
    private String paymentMode;
    private LocalDate paymentDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
