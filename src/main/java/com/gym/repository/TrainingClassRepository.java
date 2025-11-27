package com.gym.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.TrainingClass;

@Repository
public interface TrainingClassRepository extends JpaRepository<TrainingClass, Long> {
}
