package com.gym.repository;

import com.gym.entity.LedgerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<LedgerTransaction, Long> {

	@Query("""
		    SELECT COALESCE(SUM(t.debit - t.credit), 0)
		    FROM LedgerTransaction t
		    WHERE t.txnDate BETWEEN :from AND :to
		""")
		Double sumLedgerBalance(
		        @Param("from") LocalDate from,
		        @Param("to") LocalDate to
		);

}
