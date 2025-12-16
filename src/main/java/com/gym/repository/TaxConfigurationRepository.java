package com.gym.repository;

import com.gym.entity.TaxConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxConfigurationRepository extends JpaRepository<TaxConfiguration, Long> {
}