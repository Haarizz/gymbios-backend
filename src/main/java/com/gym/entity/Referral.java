package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "referrals")
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referrer_member_id")
    private Long referrerMemberId;   // instead of @ManyToOne Member

    @Column(name = "referred_name")
    private String referredName;

    @Column(name = "referred_email")
    private String referredEmail;

    @Column(name = "referred_phone")
    private String referredPhone;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "referral_date")
    private LocalDate referralDate;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "status")
    private String status;

    @Column(name = "reward_amount")
    private Double rewardAmount;

    @Column(name = "reward_rule_id")
    private Long rewardRuleId;   // instead of @ManyToOne RewardRule

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReferrerMemberId() {
        return referrerMemberId;
    }

    public void setReferrerMemberId(Long referrerMemberId) {
        this.referrerMemberId = referrerMemberId;
    }

    public String getReferredName() {
        return referredName;
    }

    public void setReferredName(String referredName) {
        this.referredName = referredName;
    }

    public String getReferredEmail() {
        return referredEmail;
    }

    public void setReferredEmail(String referredEmail) {
        this.referredEmail = referredEmail;
    }

    public String getReferredPhone() {
        return referredPhone;
    }

    public void setReferredPhone(String referredPhone) {
        this.referredPhone = referredPhone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(LocalDate referralDate) {
        this.referralDate = referralDate;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Long getRewardRuleId() {
        return rewardRuleId;
    }

    public void setRewardRuleId(Long rewardRuleId) {
        this.rewardRuleId = rewardRuleId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
