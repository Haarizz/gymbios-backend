package com.gym.repository;

import com.gym.entity.AssetTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTransactionRepository extends JpaRepository<AssetTransaction, Long> {
    // Add custom finders if needed, e.g., by type or status
}