package com.gym.controller;

import com.gym.entity.Referral;
import com.gym.service.ReferralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {

    private final ReferralService referralService;

    public ReferralController(ReferralService referralService) {
        this.referralService = referralService;
    }

    @GetMapping
    public List<Referral> getAllReferrals() {
        return referralService.getAllReferrals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Referral> getReferral(@PathVariable Long id) {
        return referralService.getReferralById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Referral> createReferral(@RequestBody ReferralRequest request) {
        Referral referral = request.toReferral();
        Referral saved = referralService.createReferral(
                referral,
                request.getReferrerMemberId(),
                request.getRewardRuleId()
        );
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Referral> updateReferral(
            @PathVariable Long id,
            @RequestBody ReferralRequest request
    ) {
        Referral referral = request.toReferral();
        Referral updated = referralService.updateReferral(
                id,
                referral,
                request.getReferrerMemberId(),
                request.getRewardRuleId()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReferral(@PathVariable Long id) {
        referralService.deleteReferral(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ReferralStats getStats() {
        List<Referral> all = referralService.getAllReferrals();

        long total = all.size();
        long successful = all.stream()
                .filter(r -> "Successful".equalsIgnoreCase(r.getStatus()))
                .count();

        double totalRewards = all.stream()
                .map(r -> r.getRewardAmount() == null ? 0.0 : r.getRewardAmount())
                .reduce(0.0, Double::sum);

        double conversion = total == 0 ? 0.0 : (successful * 100.0 / total);
        double avgReward = total == 0 ? 0.0 : totalRewards / total;

        ReferralStats stats = new ReferralStats();
        stats.setTotalReferrals(total);
        stats.setSuccessfulReferrals(successful);
        stats.setConversionRate(conversion);
        stats.setTotalRewards(totalRewards);
        stats.setAvgReward(avgReward);
        stats.setActivePrograms(3L);

        return stats;
    }

    

    // ===== Helper classes (no DTO package) =====

    public static class ReferralRequest {

        private Long id;
        private Long referrerMemberId;
        private String referredName;
        private String referredEmail;
        private String referredPhone;
        private String photoUrl;
        private String referralDate;
        private String visitDate;
        private String status;
        private String notes;
        private Double rewardAmount;
        private Long rewardRuleId;

        public Referral toReferral() {
            Referral r = new Referral();

            r.setReferredName(referredName);
            r.setReferredEmail(referredEmail);
            r.setReferredPhone(referredPhone);
            r.setPhotoUrl(photoUrl);
            r.setStatus(status);
            r.setNotes(notes);

            if (rewardAmount != null) {
                r.setRewardAmount(rewardAmount);  // now correct type
            }

            if (referralDate != null && !referralDate.isEmpty()) {
                r.setReferralDate(java.time.LocalDate.parse(referralDate));
            }
            if (visitDate != null && !visitDate.isEmpty()) {
                r.setVisitDate(java.time.LocalDate.parse(visitDate));
            }

            return r;
        }

        // Getters & Setters ...

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getReferrerMemberId() { return referrerMemberId; }
        public void setReferrerMemberId(Long referrerMemberId) { this.referrerMemberId = referrerMemberId; }

        public String getReferredName() { return referredName; }
        public void setReferredName(String referredName) { this.referredName = referredName; }

        public String getReferredEmail() { return referredEmail; }
        public void setReferredEmail(String referredEmail) { this.referredEmail = referredEmail; }

        public String getReferredPhone() { return referredPhone; }
        public void setReferredPhone(String referredPhone) { this.referredPhone = referredPhone; }

        public String getPhotoUrl() { return photoUrl; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

        public String getReferralDate() { return referralDate; }
        public void setReferralDate(String referralDate) { this.referralDate = referralDate; }

        public String getVisitDate() { return visitDate; }
        public void setVisitDate(String visitDate) { this.visitDate = visitDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }

        public Double getRewardAmount() { return rewardAmount; }
        public void setRewardAmount(Double rewardAmount) { this.rewardAmount = rewardAmount; }

        public Long getRewardRuleId() { return rewardRuleId; }
        public void setRewardRuleId(Long rewardRuleId) { this.rewardRuleId = rewardRuleId; }
    }

    public static class ReferralStats {
        private long totalReferrals;
        private long successfulReferrals;
        private double conversionRate;
        private double totalRewards;
        private double avgReward;
        private long activePrograms;

        public long getTotalReferrals() { return totalReferrals; }
        public void setTotalReferrals(long totalReferrals) { this.totalReferrals = totalReferrals; }

        public long getSuccessfulReferrals() { return successfulReferrals; }
        public void setSuccessfulReferrals(long successfulReferrals) { this.successfulReferrals = successfulReferrals; }

        public double getConversionRate() { return conversionRate; }
        public void setConversionRate(double conversionRate) { this.conversionRate = conversionRate; }

        public double getTotalRewards() { return totalRewards; }
        public void setTotalRewards(double totalRewards) { this.totalRewards = totalRewards; }

        public double getAvgReward() { return avgReward; }
        public void setAvgReward(double avgReward) { this.avgReward = avgReward; }

        public long getActivePrograms() { return activePrograms; }
        public void setActivePrograms(long activePrograms) { this.activePrograms = activePrograms; }
    }
}
