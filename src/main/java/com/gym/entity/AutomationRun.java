package com.gym.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "automation_runs")
@Getter @Setter
public class AutomationRun {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private Long automationId;
 private String name;
 private LocalDateTime executedAt;
 private Integer membersTargeted;
 private Integer membersConverted;
 private Double conversionRate;
 private Double successRate;
 public Long getId() {
	return id;
 }
 public void setId(Long id) {
	this.id = id;
 }
 public Long getAutomationId() {
	return automationId;
 }
 public void setAutomationId(Long automationId) {
	this.automationId = automationId;
 }
 public String getName() {
	return name;
 }
 public void setName(String name) {
	this.name = name;
 }
 public LocalDateTime getExecutedAt() {
	return executedAt;
 }
 public void setExecutedAt(LocalDateTime executedAt) {
	this.executedAt = executedAt;
 }
 public Integer getMembersTargeted() {
	return membersTargeted;
 }
 public void setMembersTargeted(Integer membersTargeted) {
	this.membersTargeted = membersTargeted;
 }
 public Integer getMembersConverted() {
	return membersConverted;
 }
 public void setMembersConverted(Integer membersConverted) {
	this.membersConverted = membersConverted;
 }
 public Double getConversionRate() {
	return conversionRate;
 }
 public void setConversionRate(Double conversionRate) {
	this.conversionRate = conversionRate;
 }
 public Double getSuccessRate() {
	return successRate;
 }
 public void setSuccessRate(Double successRate) {
	this.successRate = successRate;
 }
 
}
