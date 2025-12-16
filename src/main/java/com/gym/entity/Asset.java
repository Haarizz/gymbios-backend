package com.gym.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "serial", length = 255)
    private String serial;

    @Column(name = "model", length = 255)
    private String model;

    @Column(name = "vendor", length = 255)
    private String vendor;

    @Column(name = "branch", length = 255)
    private String branch;

    @Column(name = "category", length = 255)
    private String category;

    @Column(name = "location", length = 255)
    private String location;

    // purchase price stored as a formatted string like "1000 AED"
    @Column(name = "cost", length = 255)
    private String cost;

    // current value stored same way
    @Column(name = "current_value", length = 255)
    private String currentValue;

    @Column(name = "purchase_date", length = 50)
    private String purchaseDate;

    @Column(name = "warranty_expiry", length = 255)
    private String warrantyExpiry;

    @Column(name = "depreciation_rate")
    private Double depreciationRate;

    @Column(name = "status", length = 100)
    private String status;

    @Column(name = "notes", length = 2000)
    private String notes;

    // optional maintenance fields
    @Column(name = "maintenance_date", length = 50)
    private String maintenanceDate;

    @Column(name = "condition_desc", length = 255)
    private String condition;

    public Asset() {}

    // --------------------------- GETTERS & SETTERS --------------------------- //

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSerial() { return serial; }
    public void setSerial(String serial) { this.serial = serial; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCost() { return cost; }
    public void setCost(String cost) { this.cost = cost; }

    public String getCurrentValue() { return currentValue; }
    public void setCurrentValue(String currentValue) { this.currentValue = currentValue; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getWarrantyExpiry() { return warrantyExpiry; }
    public void setWarrantyExpiry(String warrantyExpiry) { this.warrantyExpiry = warrantyExpiry; }

    public Double getDepreciationRate() { return depreciationRate; }
    public void setDepreciationRate(Double depreciationRate) { this.depreciationRate = depreciationRate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getMaintenanceDate() { return maintenanceDate; }
    public void setMaintenanceDate(String maintenanceDate) { this.maintenanceDate = maintenanceDate; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
}
