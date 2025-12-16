package com.gym.controller;

import com.gym.entity.SavedReport;
import com.gym.service.CommunityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community-reports")
public class CommunityReportController {

    @Autowired
    private CommunityReportService service;

    @PostMapping
    public ResponseEntity<SavedReport> saveReport(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(service.saveReport(payload));
    }

    @GetMapping
    public ResponseEntity<List<SavedReport>> listReports() {
        return ResponseEntity.ok(service.getAllReports());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        service.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}