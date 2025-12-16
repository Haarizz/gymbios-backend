package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "salary_advance")
public class SalaryAdvance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // link to staff (optional)
    private Long staffId;
    private String employeeId;   // EMP001 style
    private String employeeName;
    private String dept;
    private String type;        // Salary Advance / Loan

    private Double requestedAmount;
    private Double approvedAmount;
    private Double paidAmount;

    private String status;      // Pending, Pending Approval, Active, Completed, Rejected
    private String reqDate;     // store as ISO date string to keep simple (yyyy-MM-dd)
    private String approvedOn;  // ISO string

    private Integer installments;
    private Double installmentAmount;
    private String deductionMode; // Monthly / Bi-weekly etc
    private Boolean autoDeduct;

    private String remarks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SalaryAdvance() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // getters & setters (IDE generate) - include all fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(Double requestedAmount) { this.requestedAmount = requestedAmount; }

    public Double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(Double approvedAmount) { this.approvedAmount = approvedAmount; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReqDate() { return reqDate; }
    public void setReqDate(String reqDate) { this.reqDate = reqDate; }

    public String getApprovedOn() { return approvedOn; }
    public void setApprovedOn(String approvedOn) { this.approvedOn = approvedOn; }

    public Integer getInstallments() { return installments; }
    public void setInstallments(Integer installments) { this.installments = installments; }

    public Double getInstallmentAmount() { return installmentAmount; }
    public void setInstallmentAmount(Double installmentAmount) { this.installmentAmount = installmentAmount; }

    public String getDeductionMode() { return deductionMode; }
    public void setDeductionMode(String deductionMode) { this.deductionMode = deductionMode; }

    public Boolean getAutoDeduct() { return autoDeduct; }
    public void setAutoDeduct(Boolean autoDeduct) { this.autoDeduct = autoDeduct; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
