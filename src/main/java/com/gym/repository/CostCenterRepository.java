package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.CostCenter;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {}
