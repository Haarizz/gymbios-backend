package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "followup_workflows")
public class FollowUpWorkflow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic info
    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 100)
    private String frequency;

    // Trigger
    @Column(length = 200)
    private String triggerTitle;

    @Column(length = 100)
    private String triggerTag;

    private Long triggerId;

    // Action
    @Column(length = 200)
    private String actionTitle;

    private Long actionId;

    // Message
    @Column(length = 4000)
    private String inAppMessage;

    @Column(length = 50)
    private String status; // "Active", "Paused", etc.

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null || status.isBlank()) status = "Active";
    }

    // ---- getters & setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public String getTriggerTitle() { return triggerTitle; }
    public void setTriggerTitle(String triggerTitle) { this.triggerTitle = triggerTitle; }

    public String getTriggerTag() { return triggerTag; }
    public void setTriggerTag(String triggerTag) { this.triggerTag = triggerTag; }

    public Long getTriggerId() { return triggerId; }
    public void setTriggerId(Long triggerId) { this.triggerId = triggerId; }

    public String getActionTitle() { return actionTitle; }
    public void setActionTitle(String actionTitle) { this.actionTitle = actionTitle; }

    public Long getActionId() { return actionId; }
    public void setActionId(Long actionId) { this.actionId = actionId; }

    public String getInAppMessage() { return inAppMessage; }
    public void setInAppMessage(String inAppMessage) { this.inAppMessage = inAppMessage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
