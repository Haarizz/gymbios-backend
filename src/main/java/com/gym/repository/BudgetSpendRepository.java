package com.gym.repository;

import com.gym.entity.BudgetSpend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BudgetSpendRepository extends JpaRepository<BudgetSpend, Long> {
    List<BudgetSpend> findByBudgetId(Long budgetId);
    List<BudgetSpend> findByMonth(LocalDate month);
}