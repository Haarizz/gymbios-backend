package com.gym.repository;

import com.gym.entity.VoucherTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherTransactionRepository extends JpaRepository<VoucherTransaction, Long> {
}
