package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "follow_ups")
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
 // Link to members.memberid (string code like "MBR-0001")
    @Column(name = "memberid", nullable = false, length = 100)
    private String memberId;

    @Column(name = "member_name", length = 255)
    private String memberName;

    @Column(name = "member_email", length = 255)
    private String memberEmail;

    @Column(name = "member_phone", length = 50)
    private String memberPhone;

    @Column(name = "subject", length = 255)
    private String subject;

    // "Call", "Email", "Whatsapp", "Meeting"
    @Column(name = "type", length = 50)
    private String type;

    // "High", "Medium", "Low"
    @Column(name = "priority", length = 50)
    private String priority;

    // "Pending", "Completed", "Overdue", "Cancelled", "Rescheduled"
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "due_time")
    private LocalTime dueTime;

    @Column(name = "assigned_staff", length = 255)
    private String assignedStaff;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // comma-separated: "renewal,billing"
    @Column(name = "tags", length = 255)
    private String tags;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "outcome", length = 255)
    private String outcome;

    public FollowUp() {
    }

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTime = dueTime;
    }

    public String getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(String assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
