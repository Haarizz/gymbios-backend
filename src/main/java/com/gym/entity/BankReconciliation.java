package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bank_reconciliation",
       uniqueConstraints = @UniqueConstraint(columnNames = {"fromDate", "toDate"}))
public class BankReconciliation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fromDate;
    private LocalDate toDate;

    private Double openingBalance;
    private Double ledgerBalance;
    private Double totalReceipts;
    private Double totalPayments;
    private Double closingBalance;
    private Double bankBalance;
    private Double difference;

    private String status;   // BALANCED / OUT_OF_BALANCE
    private String remarks;

    // ✅ GETTERS & SETTERS
    public Long getId() { return id; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public Double getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(Double openingBalance) { this.openingBalance = openingBalance; }

    public Double getLedgerBalance() { return ledgerBalance; }
    public void setLedgerBalance(Double ledgerBalance) { this.ledgerBalance = ledgerBalance; }

    public Double getTotalReceipts() { return totalReceipts; }
    public void setTotalReceipts(Double totalReceipts) { this.totalReceipts = totalReceipts; }

    public Double getTotalPayments() { return totalPayments; }
    public void setTotalPayments(Double totalPayments) { this.totalPayments = totalPayments; }

    public Double getClosingBalance() { return closingBalance; }
    public void setClosingBalance(Double closingBalance) { this.closingBalance = closingBalance; }

    public Double getBankBalance() { return bankBalance; }
    public void setBankBalance(Double bankBalance) { this.bankBalance = bankBalance; }

    public Double getDifference() { return difference; }
    public void setDifference(Double difference) { this.difference = difference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
