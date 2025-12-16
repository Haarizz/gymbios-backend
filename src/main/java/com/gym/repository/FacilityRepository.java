package com.gym.repository;

import com.gym.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByActive(boolean active);
    List<Facility> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
