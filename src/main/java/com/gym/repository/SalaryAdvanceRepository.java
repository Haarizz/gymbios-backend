package com.gym.repository;

import com.gym.entity.SalaryAdvance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SalaryAdvanceRepository extends JpaRepository<SalaryAdvance, Long> {

    List<SalaryAdvance> findByEmployeeId(String employeeId);

    // helpful convenience query
    List<SalaryAdvance> findTop50ByOrderByIdDesc();
}
