package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pos_sessions")
public class PosSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double openingCash;
    private Double closingCash;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // "ACTIVE" or "CLOSED"

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getOpeningCash() { return openingCash; }
    public void setOpeningCash(Double openingCash) { this.openingCash = openingCash; }
    public Double getClosingCash() { return closingCash; }
    public void setClosingCash(Double closingCash) { this.closingCash = closingCash; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}