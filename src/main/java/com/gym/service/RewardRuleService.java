package com.gym.service;

import com.gym.entity.RewardRule;
import com.gym.repository.RewardRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardRuleService {

    private final RewardRuleRepository rewardRuleRepository;

    public RewardRuleService(RewardRuleRepository rewardRuleRepository) {
        this.rewardRuleRepository = rewardRuleRepository;
    }

    public List<RewardRule> getAllRules() {
        return rewardRuleRepository.findAll();
    }

    public Optional<RewardRule> getRuleById(Long id) {
        return rewardRuleRepository.findById(id);
    }

    public RewardRule createRule(RewardRule rule) {
        if (rule.getActive() == null) {
            rule.setActive(true);
        }
        return rewardRuleRepository.save(rule);
    }

    public RewardRule updateRule(Long id, RewardRule updated) {
        RewardRule existing = rewardRuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reward rule not found"));

        existing.setRuleName(updated.getRuleName());
        existing.setRewardType(updated.getRewardType());
        existing.setRewardValue(updated.getRewardValue());
        existing.setEligibility(updated.getEligibility());
        existing.setConditionType(updated.getConditionType());
        existing.setExpiryDays(updated.getExpiryDays());
        existing.setActive(updated.getActive());

        return rewardRuleRepository.save(existing);
    }

    public void deleteRule(Long id) {
        rewardRuleRepository.deleteById(id);
    }
}
