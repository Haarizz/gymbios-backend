package com.gym.dto;

import lombok.Data;

@Data
public class PayrollRequest {
    private String month;
    private Integer year;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
    
}
