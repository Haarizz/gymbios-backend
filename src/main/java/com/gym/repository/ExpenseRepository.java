package com.gym.repository;

import com.gym.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT COALESCE(SUM(e.totalAmount), 0) FROM Expense e")
    BigDecimal getTotalExpenses();

    @Query("SELECT COALESCE(SUM(e.taxAmount), 0) FROM Expense e")
    BigDecimal getTotalTax();

    @Query("SELECT e.category, SUM(e.totalAmount) as total " +
           "FROM Expense e GROUP BY e.category ORDER BY total DESC")
    List<Object[]> getCategoryTotals();

    @Query("SELECT COUNT(DISTINCT e.location) FROM Expense e")
    Long getLocationCount();
}
