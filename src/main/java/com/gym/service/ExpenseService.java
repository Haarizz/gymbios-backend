package com.gym.service;

import com.gym.dto.ExpenseStatsResponse;
import com.gym.entity.Expense;
import com.gym.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updated) {
        Expense existing = getExpenseById(id);

        existing.setDate(updated.getDate());
        existing.setVendorPayee(updated.getVendorPayee());
        existing.setCategory(updated.getCategory());
        existing.setCostCenter(updated.getCostCenter());
        existing.setLocation(updated.getLocation());
        existing.setAmount(updated.getAmount());
        existing.setTaxRate(updated.getTaxRate());
        existing.setStatus(updated.getStatus());
        existing.setNotes(updated.getNotes());

        return expenseRepository.save(existing);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public ExpenseStatsResponse getStats() {
        BigDecimal totalExpenses = expenseRepository.getTotalExpenses();
        BigDecimal totalTax = expenseRepository.getTotalTax();
        Long locationCount = expenseRepository.getLocationCount();
        Long transactionCount = expenseRepository.count();

        String topCategory = null;
        List<Object[]> categoryTotals = expenseRepository.getCategoryTotals();
        if (!categoryTotals.isEmpty()) {
            topCategory = (String) categoryTotals.get(0)[0];
        }

        return new ExpenseStatsResponse(
                totalExpenses,
                totalTax,
                topCategory,
                locationCount,
                transactionCount
        );
    }

    public List<Expense> getByDateRange(LocalDate start, LocalDate end) {
        return expenseRepository.findByDateBetween(start, end);
    }
}
