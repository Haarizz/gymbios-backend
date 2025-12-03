// src/main/java/com/yourcompany/gymbios/billing/BillRepository.java
package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.Bill;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByMemberIdAndStatus(Long memberId, String status);
}
