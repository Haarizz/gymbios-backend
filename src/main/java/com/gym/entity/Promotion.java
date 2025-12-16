package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info Tab
    private String promotionName;
    private String promotionType; // E.g., Combo, Seasonal, Loyalty
    private String description;
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    @Column(unique = true)
    private String promotionCode;
    private String category;
    
    // Discount Tab
    private String discountType; // E.g., Percentage, Fixed Amount
    private Double discountValue;
    private Double minimumPurchase;
    private Double maximumDiscount;
    private Integer totalUsageLimit;
    private Integer usageLimitPerMember;

    // Targeting Tab
    private String targetAudience;
    
    @ElementCollection // Stores a list of strings (e.g., "Email", "SMS")
    @CollectionTable(name = "promotion_channels", joinColumns = @JoinColumn(name = "promotion_id"))
    @Column(name = "channel")
    private java.util.List<String> distributionChannels; 

    @ElementCollection // Stores a list of strings (e.g., "Standard Monthly", "Premium Monthly")
    @CollectionTable(name = "promotion_plans", joinColumns = @JoinColumn(name = "promotion_id"))
    @Column(name = "plan")
    private java.util.List<String> applicablePlans;

    // Settings Tab
    private String priorityLevel;
    private Boolean autoApplyAtCheckout = false;
    private Boolean canBeCombined = false;
    private Boolean publiclyVisible = false;
    private String termsAndConditions;
    
    @ElementCollection
    @CollectionTable(name = "promotion_tags", joinColumns = @JoinColumn(name = "promotion_id"))
    @Column(name = "tag")
    private java.util.List<String> tags;

    // Tracking
    private String status; // E.g., Active, Expired, Scheduled
    private Integer redemptions = 0;
    private Double revenueGenerated = 0.0;
    private Double savingsGiven = 0.0;
    
    // Getters and Setters (Omitted for brevity, but required by JPA/Lombok)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPromotionName() { return promotionName; }
    public void setPromotionName(String promotionName) { this.promotionName = promotionName; }
    public String getPromotionType() { return promotionType; }
    public void setPromotionType(String promotionType) { this.promotionType = promotionType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getPromotionCode() { return promotionCode; }
    public void setPromotionCode(String promotionCode) { this.promotionCode = promotionCode; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    public Double getDiscountValue() { return discountValue; }
    public void setDiscountValue(Double discountValue) { this.discountValue = discountValue; }
    public Double getMinimumPurchase() { return minimumPurchase; }
    public void setMinimumPurchase(Double minimumPurchase) { this.minimumPurchase = minimumPurchase; }
    public Double getMaximumDiscount() { return maximumDiscount; }
    public void setMaximumDiscount(Double maximumDiscount) { this.maximumDiscount = maximumDiscount; }
    public Integer getTotalUsageLimit() { return totalUsageLimit; }
    public void setTotalUsageLimit(Integer totalUsageLimit) { this.totalUsageLimit = totalUsageLimit; }
    public Integer getUsageLimitPerMember() { return usageLimitPerMember; }
    public void setUsageLimitPerMember(Integer usageLimitPerMember) { this.usageLimitPerMember = usageLimitPerMember; }
    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }
    public List<String> getDistributionChannels() { return distributionChannels; }
    public void setDistributionChannels(java.util.List<String> distributionChannels) { this.distributionChannels = distributionChannels; }
    public List<String> getApplicablePlans() { return applicablePlans; }
    public void setApplicablePlans(java.util.List<String> applicablePlans) { this.applicablePlans = applicablePlans; }
    public String getPriorityLevel() { return priorityLevel; }
    public void setPriorityLevel(String priorityLevel) { this.priorityLevel = priorityLevel; }
    public Boolean getAutoApplyAtCheckout() { return autoApplyAtCheckout; }
    public void setAutoApplyAtCheckout(Boolean autoApplyAtCheckout) { this.autoApplyAtCheckout = autoApplyAtCheckout; }
    public Boolean getCanBeCombined() { return canBeCombined; }
    public void setCanBeCombined(Boolean canBeCombined) { this.canBeCombined = canBeCombined; }
    public Boolean getPubliclyVisible() { return publiclyVisible; }
    public void setPubliclyVisible(Boolean publiclyVisible) { this.publiclyVisible = publiclyVisible; }
    public String getTermsAndConditions() { return termsAndConditions; }
    public void setTermsAndConditions(String termsAndConditions) { this.termsAndConditions = termsAndConditions; }
    public List<String> getTags() { return tags; }
    public void setTags(java.util.List<String> tags) { this.tags = tags; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getRedemptions() { return redemptions; }
    public void setRedemptions(Integer redemptions) { this.redemptions = redemptions; }
    public Double getRevenueGenerated() { return revenueGenerated; }
    public void setRevenueGenerated(Double revenueGenerated) { this.revenueGenerated = revenueGenerated; }
    public Double getSavingsGiven() { return savingsGiven; }
    public void setSavingsGiven(Double savingsGiven) { this.savingsGiven = savingsGiven; }
    // ... other getters and setters for the remaining fields
}