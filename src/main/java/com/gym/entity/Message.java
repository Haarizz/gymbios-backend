// src/main/java/com/gym/entity/Message.java
package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Shown in UI as message title
    @Column(length = 255)
    private String title;

    // Email / SMS / Whatsapp
    @Column(name = "message_type", length = 50)
    private String messageType;

    // Sent / Scheduled / Delivered, etc.
    @Column(length = 50)
    private String status;

    // Number of recipients
    @Column(name = "recipients_count")
    private Integer recipientsCount;

    // Comma-separated list of member IDs
    @Column(name = "recipient_ids", length = 1000)
    private String recipientIds;

    @Column(length = 255)
    private String subject;

    @Column(columnDefinition = "text")
    private String content;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    // Analytics fields
    @Column(name = "open_rate")
    private Double openRate;

    @Column(name = "click_rate")
    private Double clickRate;

    // Cost in AED
    private Double cost;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // ---- getters & setters ----

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

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRecipientsCount() {
        return recipientsCount;
    }

    public void setRecipientsCount(Integer recipientsCount) {
        this.recipientsCount = recipientsCount;
    }

    public String getRecipientIds() {
        return recipientIds;
    }

    public void setRecipientIds(String recipientIds) {
        this.recipientIds = recipientIds;
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

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Double getOpenRate() {
        return openRate;
    }

    public void setOpenRate(Double openRate) {
        this.openRate = openRate;
    }

    public Double getClickRate() {
        return clickRate;
    }

    public void setClickRate(Double clickRate) {
        this.clickRate = clickRate;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
