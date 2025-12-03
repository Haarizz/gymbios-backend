package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.JobOpening;

@Repository
public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {}
