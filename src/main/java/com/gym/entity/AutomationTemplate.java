package com.gym.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "automation_templates")
@Getter @Setter
public class AutomationTemplate {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String title;
 private String type;

 @Column(columnDefinition = "TEXT")
 private String content;

 private Integer usage = 0;

 public Long getId() {
	return id;
 }

 public void setId(Long id) {
	this.id = id;
 }

 public String getTitle() {
	return title;
 }

 public void setTitle(String title) {
	this.title = title;
 }

 public String getType() {
	return type;
 }

 public void setType(String type) {
	this.type = type;
 }

 public String getContent() {
	return content;
 }

 public void setContent(String content) {
	this.content = content;
 }

 public Integer getUsage() {
	return usage;
 }

 public void setUsage(Integer usage) {
	this.usage = usage;
 }
 
}
