package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.ApiIntegrationEntity;

@Repository
public interface ApiIntegrationRepository extends JpaRepository<ApiIntegrationEntity, Long> {}
