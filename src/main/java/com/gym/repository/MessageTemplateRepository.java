// src/main/java/com/gym/repository/MessageTemplateRepository.java
package com.gym.repository;

import com.gym.entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
}
