package com.gym.repository;
import com.gym.entity.PosCashMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosCashMovementRepository extends JpaRepository<PosCashMovement, Long> {}