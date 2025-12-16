// src/main/java/com/gym/service/MessageService.java
package com.gym.service;

import com.gym.entity.Message;
import com.gym.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public List<Message> findAll() {
        // if you want sorted:
        try {
            return repo.findAllByOrderBySentAtDescCreatedAtDesc();
        } catch (Exception e) {
            return repo.findAll();
        }
    }

    public Message create(Message m) {
        if (m.getTitle() == null || m.getTitle().isBlank()) {
            m.setTitle(m.getSubject() != null ? m.getSubject() : "(No subject)");
        }
        if (m.getSentAt() == null) {
            m.setSentAt(LocalDateTime.now());
        }
        if (m.getOpenRate() == null) m.setOpenRate(0.0);
        if (m.getClickRate() == null) m.setClickRate(0.0);
        if (m.getCost() == null) m.setCost(0.0);
        m.setCreatedAt(LocalDateTime.now());
        return repo.save(m);
    }
}
