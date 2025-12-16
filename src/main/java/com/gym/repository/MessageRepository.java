// src/main/java/com/gym/repository/MessageRepository.java
package com.gym.repository;

import com.gym.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Optional: list latest first
    List<Message> findAllByOrderBySentAtDescCreatedAtDesc();
}
