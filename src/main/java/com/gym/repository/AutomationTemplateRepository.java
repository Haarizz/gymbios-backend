package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.AutomationTemplate;


@Repository
public interface AutomationTemplateRepository extends JpaRepository<AutomationTemplate, Long> { }