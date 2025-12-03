package com.gym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountCode;
    private String name;
    private String accountGroup;
    private String branch;
    private Long costCenterId;
    private Double openingBalance;
    private Boolean active = true;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountGroup() {
		return accountGroup;
	}
	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Long getCostCenterId() {
		return costCenterId;
	}
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;
	}
	public Double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
    
    
}

