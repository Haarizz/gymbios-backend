package com.gym.repository;

import com.gym.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    // You can add custom queries here if needed, e.g., finding by email
}