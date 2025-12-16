package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.GymOSConfig;

@Repository
public interface GymOSConfigRepository extends JpaRepository<GymOSConfig, Long> {}
