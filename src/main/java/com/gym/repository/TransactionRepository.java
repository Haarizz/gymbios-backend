package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.LedgerTransaction;

@Repository
public interface TransactionRepository extends JpaRepository<LedgerTransaction, Long> {}
