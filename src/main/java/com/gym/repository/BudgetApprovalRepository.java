package com.gym.repository;

import com.gym.entity.BudgetApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetApprovalRepository extends JpaRepository<BudgetApproval, Long> {
}
