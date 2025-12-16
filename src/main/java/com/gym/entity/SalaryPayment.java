package com.gym.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "salary_payment")
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long staffId;          // staff.id
    private String staffName;      // firstname + lastname
    private String employeeId;     // EMP001, EMP002...

    private String role;
    private String department;
    private String branch;

    private String month;
    private Double amount;
    private String status;
    private String remarks;
    private Double BankAmount;
    private Double CashAmount;
    private String PaymentMode;
    private String PaymentDate;
    
    public SalaryPayment() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }



	public Double getBankAmount() {
		return BankAmount;
	}

	public void setBankAmount(Double bankAmount) {
		BankAmount = bankAmount;
	}

	public Double getCashAmount() {
		return CashAmount;
	}

	public void setCashAmount(Double cashAmount) {
		CashAmount = cashAmount;
	}

	public String getPaymentMode() {
		return PaymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}

	public String getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		PaymentDate = paymentDate;
	}


}
