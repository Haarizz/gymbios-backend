package com.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.Automation;



@Repository
public interface AutomationRepository extends JpaRepository<Automation, Long> { }
