// src/main/java/com/gym/dto/BookingDto.java
package com.gym.dto;

public class BookingDto {
    private Long id;
    private Long memberId;
    private Long trainingClassId;
    private String date;
    private String time;
    private Double price;
    private String status;
    private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getTrainingClassId() {
		return trainingClassId;
	}
	public void setTrainingClassId(Long trainingClassId) {
		this.trainingClassId = trainingClassId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

    // getters & setters
    
    
}
