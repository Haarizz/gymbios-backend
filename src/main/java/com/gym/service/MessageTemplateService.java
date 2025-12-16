// src/main/java/com/gym/service/MessageTemplateService.java
package com.gym.service;

import com.gym.entity.MessageTemplate;
import com.gym.repository.MessageTemplateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageTemplateService {

    private final MessageTemplateRepository repo;

    public MessageTemplateService(MessageTemplateRepository repo) {
        this.repo = repo;
    }

    public List<MessageTemplate> findAll() {
        return repo.findAll();
    }

    public MessageTemplate create(MessageTemplate t) {
        t.setId(null);
        if (t.getUsageCount() == null) {
            t.setUsageCount(0);
        }
        t.setCreatedAt(LocalDateTime.now());
        t.setUpdatedAt(LocalDateTime.now());
        return repo.save(t);
    }

    public MessageTemplate update(Long id, MessageTemplate t) {
        MessageTemplate existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found: " + id));

        existing.setName(t.getName());
        existing.setCategory(t.getCategory());
        existing.setMessageType(t.getMessageType());
        existing.setSubject(t.getSubject());
        existing.setContent(t.getContent());
        existing.setVariables(t.getVariables());
        if (t.getUsageCount() != null) {
            existing.setUsageCount(t.getUsageCount());
        }
        existing.setUpdatedAt(LocalDateTime.now());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
