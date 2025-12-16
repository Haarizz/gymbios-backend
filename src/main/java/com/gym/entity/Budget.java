package com.gym.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // core fields (strings only — no joins)
    private String code;
    private String category;     // category name (from frontend)
    private String department;   // department name (frontend derives from staff)
    private String branch;       // branch name (frontend derives from staff)
    private String responsible;  // responsible person's name (frontend derived)
    private String type;         // Monthly/Quarterly/etc
    private String status;
    @Column(columnDefinition = "text")
    private String notes;

    private BigDecimal amount = BigDecimal.ZERO;
    private Integer alertThresholdPct = 0;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // getters & setters omitted for brevity — include them in your file
    // (or generate with your IDE)
    // ... (standard getters and setters)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getResponsible() { return responsible; }
    public void setResponsible(String responsible) { this.responsible = responsible; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getAlertThresholdPct() { return alertThresholdPct; }
    public void setAlertThresholdPct(Integer alertThresholdPct) { this.alertThresholdPct = alertThresholdPct; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
