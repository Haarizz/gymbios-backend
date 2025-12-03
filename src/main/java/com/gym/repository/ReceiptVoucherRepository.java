// src/main/java/com/yourapp/finance/repository/ReceiptVoucherRepository.java
package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.ReceiptVoucher;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptVoucherRepository extends JpaRepository<ReceiptVoucher, Long> {

    List<ReceiptVoucher> findByVoucherDate(LocalDate date);

}
