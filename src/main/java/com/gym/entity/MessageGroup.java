package com.gym.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "message_groups")
public class MessageGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String description;

    /* --------------------------
       MEMBERSHIP STATUS FILTERS
       -------------------------- */
    @Column(name = "status_active")
    private boolean statusActive;

    @Column(name = "status_expired")
    private boolean statusExpired;

    @Column(name = "status_frozen")
    private boolean statusFrozen;

    @Column(name = "status_cancelled")
    private boolean statusCancelled;

    /* --------------------------
       MEMBERSHIP PLAN FILTERS
       -------------------------- */
    @Column(name = "plan_standard_monthly")
    private boolean planStandardMonthly;

    @Column(name = "plan_standard_annual")
    private boolean planStandardAnnual;

    @Column(name = "plan_premium_monthly")
    private boolean planPremiumMonthly;

    @Column(name = "plan_premium_annual")
    private boolean planPremiumAnnual;

    @Column(name = "vip_only")
    private boolean vipOnly;

    /* --------------------------
       GETTERS & SETTERS
       -------------------------- */

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

    public boolean isStatusActive() {
        return statusActive;
    }

    public void setStatusActive(boolean statusActive) {
        this.statusActive = statusActive;
    }

    public boolean isStatusExpired() {
        return statusExpired;
    }

    public void setStatusExpired(boolean statusExpired) {
        this.statusExpired = statusExpired;
    }

    public boolean isStatusFrozen() {
        return statusFrozen;
    }

    public void setStatusFrozen(boolean statusFrozen) {
        this.statusFrozen = statusFrozen;
    }

    public boolean isStatusCancelled() {
        return statusCancelled;
    }

    public void setStatusCancelled(boolean statusCancelled) {
        this.statusCancelled = statusCancelled;
    }

    public boolean isPlanStandardMonthly() {
        return planStandardMonthly;
    }

    public void setPlanStandardMonthly(boolean planStandardMonthly) {
        this.planStandardMonthly = planStandardMonthly;
    }

    public boolean isPlanStandardAnnual() {
        return planStandardAnnual;
    }

    public void setPlanStandardAnnual(boolean planStandardAnnual) {
        this.planStandardAnnual = planStandardAnnual;
    }

    public boolean isPlanPremiumMonthly() {
        return planPremiumMonthly;
    }

    public void setPlanPremiumMonthly(boolean planPremiumMonthly) {
        this.planPremiumMonthly = planPremiumMonthly;
    }

    public boolean isPlanPremiumAnnual() {
        return planPremiumAnnual;
    }

    public void setPlanPremiumAnnual(boolean planPremiumAnnual) {
        this.planPremiumAnnual = planPremiumAnnual;
    }

    public boolean isVipOnly() {
        return vipOnly;
    }

    public void setVipOnly(boolean vipOnly) {
        this.vipOnly = vipOnly;
    }
}
