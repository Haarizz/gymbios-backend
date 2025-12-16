package com.gym.repository;

import com.gym.entity.TaxFiling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxFilingRepository extends JpaRepository<TaxFiling, Long> {
    List<TaxFiling> findByConfigId(Long configId);
    void deleteByConfigId(Long configId);
}