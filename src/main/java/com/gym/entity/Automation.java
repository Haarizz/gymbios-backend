// src/main/java/com/gym/automations/entity/Automation.java
package com.gym.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "automations")
@Getter @Setter
public class Automation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private String trigger;

    @ElementCollection
    @CollectionTable(name = "automation_actions", joinColumns = @JoinColumn(name = "automation_id"))
    @Column(name = "action")
    private List<String> actions;

    private Boolean active = true;

    private LocalDateTime nextRun;
    private LocalDateTime lastRun;

    private Integer totalRuns;
    private Double successRate;
    private Double conversionRate;
    private Integer membersEngaged;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String frequency;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getNextRun() {
		return nextRun;
	}

	public void setNextRun(LocalDateTime nextRun) {
		this.nextRun = nextRun;
	}

	public LocalDateTime getLastRun() {
		return lastRun;
	}

	public void setLastRun(LocalDateTime lastRun) {
		this.lastRun = lastRun;
	}

	public Integer getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(Integer totalRuns) {
		this.totalRuns = totalRuns;
	}

	public Double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}

	public Double getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}

	public Integer getMembersEngaged() {
		return membersEngaged;
	}

	public void setMembersEngaged(Integer membersEngaged) {
		this.membersEngaged = membersEngaged;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
    
    
}
