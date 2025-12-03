
package com.gym.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payroll_cycles")
public class PayrollCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;
    private Integer year;
    private Integer employeesCount;
    private BigDecimal totalAmount;
    private String status; // PENDING, APPROVED, DISBURSED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getEmployeesCount() {
		return employeesCount;
	}
	public void setEmployeesCount(Integer employeesCount) {
		this.employeesCount = employeesCount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Transient
	public String getGrossAmount() {
	    return totalAmount != null ? totalAmount.toPlainString() : "0";
	}

	@Transient
	public String getDeductions() {
	    return "0"; // no deductions yet
	}

	@Transient
	public String getNetAmount() {
	    return totalAmount != null ? totalAmount.toPlainString() : "0";
	}

	@Transient
	public String getTotalAmountFormatted() {
	    return totalAmount != null ? "AED " + totalAmount.toPlainString() : "AED 0";
	}

	@Transient
	public String getCreated() {
	    return createdAt != null ? createdAt.toLocalDate().toString() : "-";
	}

	@Transient
	public String getDisbursed() {
	    return "-"; // no disbursement yet
	}

    // getters/setters
    
    
    // (Use Lombok @Data if you prefer)
}
