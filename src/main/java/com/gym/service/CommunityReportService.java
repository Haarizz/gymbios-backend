package com.gym.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.entity.SavedReport;
import com.gym.repository.SavedReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommunityReportService {

    @Autowired
    private SavedReportRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SavedReport saveReport(Map<String, Object> payload) {
        SavedReport report = new SavedReport();
        report.setName((String) payload.getOrDefault("name", "Untitled Report"));

        try {
            // Convert nested objects to JSON strings
            if (payload.containsKey("filters")) {
                report.setFilters(objectMapper.writeValueAsString(payload.get("filters")));
            }
            if (payload.containsKey("summary")) {
                report.setSummaryJson(objectMapper.writeValueAsString(payload.get("summary")));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing report JSON", e);
        }

        return repository.save(report);
    }

    public List<SavedReport> getAllReports() {
        return repository.findAllByOrderByCreatedAtDesc();
    }
    
    public void deleteReport(Long id) {
        repository.deleteById(id);
    }
}