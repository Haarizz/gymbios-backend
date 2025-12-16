package com.gym.dto;

import java.math.BigDecimal;

public class ExpenseStatsResponse {

    private BigDecimal totalExpenses;
    private BigDecimal totalTaxPaid;
    private String topCategory;
    private Long locationCount;
    private Long transactionCount;

    public ExpenseStatsResponse() {}

    public ExpenseStatsResponse(BigDecimal totalExpenses,
                                BigDecimal totalTaxPaid,
                                String topCategory,
                                Long locationCount,
                                Long transactionCount) {
        this.totalExpenses = totalExpenses;
        this.totalTaxPaid = totalTaxPaid;
        this.topCategory = topCategory;
        this.locationCount = locationCount;
        this.transactionCount = transactionCount;
    }

    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(BigDecimal totalExpenses) { this.totalExpenses = totalExpenses; }

    public BigDecimal getTotalTaxPaid() { return totalTaxPaid; }
    public void setTotalTaxPaid(BigDecimal totalTaxPaid) { this.totalTaxPaid = totalTaxPaid; }

    public String getTopCategory() { return topCategory; }
    public void setTopCategory(String topCategory) { this.topCategory = topCategory; }

    public Long getLocationCount() { return locationCount; }
    public void setLocationCount(Long locationCount) { this.locationCount = locationCount; }

    public Long getTransactionCount() { return transactionCount; }
    public void setTransactionCount(Long transactionCount) { this.transactionCount = transactionCount; }
}
