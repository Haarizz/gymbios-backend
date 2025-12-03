package com.gym.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wastage_return")
public class WastageReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ FIX: Added the voucherNumber field
    private String voucherNumber; 
    
    // --- Core Fields ---
    private String voucherType;   // WASTAGE or RETURN
    private String date;          // dd-MM-yyyy
    private String reason;
    private String location;
    // Assuming products is stored as a JSON string in the DB
    @Column(columnDefinition = "TEXT") 
    private String products;      
    private String notes;
    private String status;        // Draft, Pending, Approved, etc.
    private Double totalValue;
    private String partyType;     // Supplier, Customer, Other (for RETURN)

    public WastageReturn() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // ✅ FIX: Getter and Setter for the new manual field
    public String getVoucherNumber() { return voucherNumber; }
    public void setVoucherNumber(String voucherNumber) { this.voucherNumber = voucherNumber; }

    public String getVoucherType() { return voucherType; }
    public void setVoucherType(String voucherType) { this.voucherType = voucherType; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getProducts() { return products; }
    public void setProducts(String products) { this.products = products; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getTotalValue() { return totalValue; }
    public void setTotalValue(Double totalValue) { this.totalValue = totalValue; }
    
    public String getPartyType() { return partyType; }
    public void setPartyType(String partyType) { this.partyType = partyType; }
}