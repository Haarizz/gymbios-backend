package com.gym.repository;

import com.gym.entity.SavedReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedReportRepository extends JpaRepository<SavedReport, Long> {
    // Fetch reports ordered by newest first
    List<SavedReport> findAllByOrderByCreatedAtDesc();
}