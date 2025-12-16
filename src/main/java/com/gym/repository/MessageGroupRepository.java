// src/main/java/com/gym/repository/MessageGroupRepository.java
package com.gym.repository;

import com.gym.entity.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {
}
