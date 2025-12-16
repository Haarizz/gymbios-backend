package com.gym.controller;

import com.gym.entity.RewardRule;
import com.gym.service.RewardRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-rules")
public class RewardRuleController {

    private final RewardRuleService rewardRuleService;

    public RewardRuleController(RewardRuleService rewardRuleService) {
        this.rewardRuleService = rewardRuleService;
    }

    @GetMapping
    public List<RewardRule> getAll() {
        return rewardRuleService.getAllRules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RewardRule> getOne(@PathVariable Long id) {
        return rewardRuleService.getRuleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RewardRule create(@RequestBody RewardRule rule) {
        return rewardRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public RewardRule update(@PathVariable Long id, @RequestBody RewardRule rule) {
        return rewardRuleService.updateRule(id, rule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rewardRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
