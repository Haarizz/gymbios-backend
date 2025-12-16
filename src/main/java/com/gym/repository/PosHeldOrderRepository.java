package com.gym.repository;
import com.gym.entity.PosHeldOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosHeldOrderRepository extends JpaRepository<PosHeldOrder, Long> {}