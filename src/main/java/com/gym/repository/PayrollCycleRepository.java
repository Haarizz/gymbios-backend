
package com.gym.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.PayrollCycle;

import java.util.List;

@Repository
public interface PayrollCycleRepository extends JpaRepository<PayrollCycle, Long> {
    long countByStatus(String status);
    List<PayrollCycle> findTop5ByOrderByCreatedAtDesc();
    List<PayrollCycle> findByStatus(String status);

}
