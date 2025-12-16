package com.gym.dto;

import java.time.LocalDate;

public class BankReconciliationFinalizeRequest {

    private LocalDate fromDate;
    private LocalDate toDate;

    private Double openingBalance;
    private Double totalReceipts;
    private Double totalPayments;

    private Double ledgerBalance;
    private Double closingBalance;

    private String remarks;

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Double getTotalReceipts() {
		return totalReceipts;
	}

	public void setTotalReceipts(Double totalReceipts) {
		this.totalReceipts = totalReceipts;
	}

	public Double getTotalPayments() {
		return totalPayments;
	}

	public void setTotalPayments(Double totalPayments) {
		this.totalPayments = totalPayments;
	}

	public Double getLedgerBalance() {
		return ledgerBalance;
	}

	public void setLedgerBalance(Double ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    // ✅ Getters & Setters
}
