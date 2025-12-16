package com.gym.controller;

import com.gym.entity.TaxConfiguration;
import com.gym.entity.TaxFiling;
import com.gym.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    // --- Configuration Endpoints ---

    @GetMapping("/config")
    public List<TaxConfiguration> getAllConfigs() {
        return taxService.getAllConfigs();
    }

    @PostMapping("/config")
    public ResponseEntity<TaxConfiguration> createConfig(@RequestBody TaxConfiguration config) {
        return ResponseEntity.ok(taxService.createConfig(config));
    }

    @PutMapping("/config/{id}")
    public ResponseEntity<TaxConfiguration> updateConfig(@PathVariable Long id, @RequestBody TaxConfiguration config) {
        return ResponseEntity.ok(taxService.updateConfig(id, config));
    }

    @DeleteMapping("/config/{id}")
    public ResponseEntity<?> deleteConfig(@PathVariable Long id) {
        taxService.deleteConfig(id);
        return ResponseEntity.ok().build();
    }

    // --- Filing Endpoints ---

    @GetMapping("/filings")
    public List<TaxFiling> getAllFilings() {
        return taxService.getAllFilings();
    }

    @PutMapping("/filings/{id}")
    public ResponseEntity<TaxFiling> updateFiling(@PathVariable Long id, @RequestBody TaxFiling filing) {
        return ResponseEntity.ok(taxService.updateFiling(id, filing));
    }
}