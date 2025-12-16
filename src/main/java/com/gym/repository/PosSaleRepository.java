package com.gym.repository;
import com.gym.entity.PosSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosSaleRepository extends JpaRepository<PosSale, Long> {}