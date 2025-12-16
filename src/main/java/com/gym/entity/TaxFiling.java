package com.gym.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tax_filings")
public class TaxFiling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // We store the config ID or link to keep track of the relationship
    @Column(name = "config_id")
    private Long configId;

    private String type;
    private String period;    // e.g., "Q4 2025"
    private String dueDate;   // e.g., "31 Dec 2025"
    private String filedDate; // Nullable
    private Double amount;
    private String status;    // Pending, Filed, Overdue
    private String notes;
    private Integer documents; // Count of documents

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getConfigId() { return configId; }
    public void setConfigId(Long configId) { this.configId = configId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getFiledDate() { return filedDate; }
    public void setFiledDate(String filedDate) { this.filedDate = filedDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Integer getDocuments() { return documents; }
    public void setDocuments(Integer documents) { this.documents = documents; }
}