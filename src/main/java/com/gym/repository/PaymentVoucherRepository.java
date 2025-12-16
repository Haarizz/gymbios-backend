package com.gym.repository;

import com.gym.entity.PaymentVoucher;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentVoucherRepository extends JpaRepository<PaymentVoucher, Long> {

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM PaymentVoucher p
        WHERE p.paymentDate BETWEEN :from AND :to
    """)
    Double sumBankPayments(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );
}
