package com.gym.repository;

import com.gym.entity.ReceiptVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptVoucherRepository extends JpaRepository<ReceiptVoucher, Long> {

    @Query("""
        SELECT COALESCE(SUM(r.amount), 0)
        FROM ReceiptVoucher r
        WHERE r.voucherDate BETWEEN :from AND :to
    """)
    Double sumBankReceipts(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    List<ReceiptVoucher> findByVoucherDate(LocalDate date);
}
