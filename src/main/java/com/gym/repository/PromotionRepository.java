package com.gym.repository;

import com.gym.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    // Custom query methods can be added here if needed, 
    // e.g., Promotion findByPromotionCode(String code);
}