// src/main/java/com/gym/service/MessageGroupService.java
package com.gym.service;

import com.gym.entity.MessageGroup;
import com.gym.repository.MessageGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageGroupService {

    private final MessageGroupRepository repo;

    public MessageGroupService(MessageGroupRepository repo) {
        this.repo = repo;
    }

    public List<MessageGroup> findAll() {
        return repo.findAll();
    }

    public MessageGroup create(MessageGroup g) {
        g.setId(null);
        return repo.save(g);
    }

    public MessageGroup update(Long id, MessageGroup payload) {
        MessageGroup existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found: " + id));

        existing.setName(payload.getName());
        existing.setDescription(payload.getDescription());
        existing.setStatusActive(payload.isStatusActive());
        existing.setStatusExpired(payload.isStatusExpired());
        existing.setStatusFrozen(payload.isStatusFrozen());
        existing.setStatusCancelled(payload.isStatusCancelled());
        existing.setPlanStandardMonthly(payload.isPlanStandardMonthly());
        existing.setPlanStandardAnnual(payload.isPlanStandardAnnual());
        existing.setPlanPremiumMonthly(payload.isPlanPremiumMonthly());
        existing.setPlanPremiumAnnual(payload.isPlanPremiumAnnual());
        existing.setVipOnly(payload.isVipOnly());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
