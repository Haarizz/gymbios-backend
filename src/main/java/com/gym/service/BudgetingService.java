package com.gym.service;

import com.gym.entity.Budget;
import com.gym.entity.BudgetRule;
import com.gym.entity.BudgetSpend;
import com.gym.repository.BudgetRepository;
import com.gym.repository.BudgetRuleRepository;
import com.gym.repository.BudgetSpendRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetingService {

    private final BudgetRepository budgetRepo;
    private final BudgetSpendRepository spendRepo;
    private final BudgetRuleRepository ruleRepo;

    public BudgetingService(
            BudgetRepository budgetRepo,
            BudgetSpendRepository spendRepo,
            BudgetRuleRepository ruleRepo
    ) {
        this.budgetRepo = budgetRepo;
        this.spendRepo = spendRepo;
        this.ruleRepo = ruleRepo;
    }

    // ----------------------------------------
    // List budgets
    // ----------------------------------------
    public List<Budget> listBudgets() {
        return budgetRepo.findAll();
    }

    // ----------------------------------------
    // totalBudget = sum(amount)
    // ----------------------------------------
    public BigDecimal totalBudget() {
        return budgetRepo.findAll().stream()
                .map(b -> b.getAmount() != null ? b.getAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ----------------------------------------
    // totalSpent = sum of all BudgetSpend entries
    // ----------------------------------------
    public BigDecimal totalSpent() {
        return spendRepo.findAll().stream()
                .map(s -> s.getSpent() != null ? s.getSpent() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ----------------------------------------
    // totalSpent(month) for BvA analytics
    // ----------------------------------------
    public BigDecimal totalSpent(LocalDate monthStart) {
        return spendRepo.findByMonth(monthStart).stream()
                .map(s -> s.getSpent() != null ? s.getSpent() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ----------------------------------------
    // totalSpent for a single budget
    // ----------------------------------------
    public BigDecimal getTotalSpentForBudget(Long budgetId) {
        return spendRepo.findByBudgetId(budgetId).stream()
                .map(s -> s.getSpent() != null ? s.getSpent() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ----------------------------------------
    // rules count (if exists)
    // ----------------------------------------
    public int rulesCount() {
        if (ruleRepo == null) return 0;
        return (int) ruleRepo.count();
    }
}
