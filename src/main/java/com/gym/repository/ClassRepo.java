package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gym.entity.ClassEntity;

public interface ClassRepo extends JpaRepository<ClassEntity,Integer> {
}
