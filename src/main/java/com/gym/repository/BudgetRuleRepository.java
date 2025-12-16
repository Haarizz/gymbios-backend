package com.gym.repository;

import com.gym.entity.BudgetRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRuleRepository extends JpaRepository<BudgetRule, Long> {
}