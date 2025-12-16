package com.gym.repository;

import com.gym.entity.BankReconciliation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BankReconciliationRepository
        extends JpaRepository<BankReconciliation, Long> {

    Optional<BankReconciliation> findByFromDateAndToDate(
            LocalDate fromDate, LocalDate toDate
    );
}
