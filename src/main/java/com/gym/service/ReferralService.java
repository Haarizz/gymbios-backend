package com.gym.service;

import com.gym.entity.Referral;
import com.gym.repository.ReferralRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ReferralService {

    private final ReferralRepository referralRepository;

    public ReferralService(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }

    public List<Referral> getAllReferrals() {
        return referralRepository.findAll();
    }

    public Optional<Referral> getReferralById(Long id) {
        return referralRepository.findById(id);
    }

    public Referral createReferral(Referral referral, Long referrerMemberId, Long rewardRuleId) {

        referral.setReferrerMemberId(referrerMemberId);
        referral.setRewardRuleId(rewardRuleId);

        if (referral.getRewardAmount() == null) {
            referral.setRewardAmount(0.0); // FIXED
        }

        return referralRepository.save(referral);
    }

    public Referral updateReferral(Long id, Referral updated, Long referrerMemberId, Long rewardRuleId) {

        Referral existing = referralRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Referral not found"));

        existing.setReferrerMemberId(referrerMemberId);
        existing.setRewardRuleId(rewardRuleId);

        existing.setReferredName(updated.getReferredName());
        existing.setReferredEmail(updated.getReferredEmail());
        existing.setReferredPhone(updated.getReferredPhone());
        existing.setPhotoUrl(updated.getPhotoUrl());
        existing.setReferralDate(updated.getReferralDate());
        existing.setVisitDate(updated.getVisitDate());
        existing.setStatus(updated.getStatus());
        existing.setNotes(updated.getNotes());

        if (updated.getRewardAmount() != null) {
            existing.setRewardAmount(updated.getRewardAmount()); // FIXED
        }

        return referralRepository.save(existing);
    }

    public void deleteReferral(Long id) {
        referralRepository.deleteById(id);
    }
}
