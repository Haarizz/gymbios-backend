package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.GymOSConfig;

@Repository
public interface GymOSRepository extends JpaRepository<GymOSConfig, Long> {
}
