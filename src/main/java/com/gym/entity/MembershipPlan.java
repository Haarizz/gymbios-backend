package com.gym.entity;

import jakarta.persistence.*;

@Entity
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // Basic Monthly
    private String category;        // Membership / Personal Training
    private String type;            // Individual / Family / Corporate
    private Integer durationValue;
    private String durationType;
        // 1 month, 3 months, 12 months, etc.
    private Double price;           // AED 49.99
    private Integer discount;       // percentage

    private String status;          // Active / Inactive

    private String trainers;        // Store as CSV or JSON: "John, Sarah"


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Integer getDurationValue() {
		return durationValue;
	}

	public void setDurationValue(Integer durationValue) {
		this.durationValue = durationValue;
	}

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrainers() {
		return trainers;
	}

	public void setTrainers(String trainers) {
		this.trainers = trainers;
	}


    
}
