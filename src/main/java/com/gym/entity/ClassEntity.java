package com.gym.entity;

import jakarta.persistence.*;



@Entity
@Table(name="classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    private String instructor_id;

    private Integer max_capacity;

    private Integer duration_minutes;

    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        active,inactive
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(String instructor_id) {
		this.instructor_id = instructor_id;
	}

	public Integer getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(Integer max_capacity) {
		this.max_capacity = max_capacity;
	}

	public Integer getDuration_minutes() {
		return duration_minutes;
	}

	public void setDuration_minutes(Integer duration_minutes) {
		this.duration_minutes = duration_minutes;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
    
    
}
