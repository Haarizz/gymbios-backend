package com.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.AutomationRun;

@Repository
public interface AutomationRunRepository extends JpaRepository<AutomationRun, Long> {
    List<AutomationRun> findTop10ByOrderByExecutedAtDesc();
}
