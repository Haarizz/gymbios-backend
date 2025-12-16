package com.gym.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tax_configurations")
public class TaxConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // e.g., Corporate Tax, VAT

    @Column(nullable = false)
    private String frequency; // Monthly, Quarterly, Annually

    @Column(nullable = false)
    private String rate; // e.g., "5%", "9%"

    @ElementCollection
    @CollectionTable(name = "tax_config_accounts", joinColumns = @JoinColumn(name = "config_id"))
    @Column(name = "account_name")
    private List<String> accounts;

    private String status; // Active, Inactive

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getRate() { return rate; }
    public void setRate(String rate) { this.rate = rate; }

    public List<String> getAccounts() { return accounts; }
    public void setAccounts(List<String> accounts) { this.accounts = accounts; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}