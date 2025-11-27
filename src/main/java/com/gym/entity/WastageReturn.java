package com.gym.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wastage_return")
public class WastageReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voucherType;   // WASTAGE or RETURN
    private String date;          // dd-MM-yyyy
    private String reason;
    private String location;
    private String products;      // JSON string (ex: [{"p":"Product A","q":5}])
    private String notes;
    private String status;        // Pending, Completed, etc.
    private Double totalValue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
}
