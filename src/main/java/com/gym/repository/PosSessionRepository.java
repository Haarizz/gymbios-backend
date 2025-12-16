package com.gym.repository;
import com.gym.entity.PosSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosSessionRepository extends JpaRepository<PosSession, Long> {}