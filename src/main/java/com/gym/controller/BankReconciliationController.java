package com.gym.controller;

import com.gym.service.BankReconciliationService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping("/bank-reconciliation")
public class BankReconciliationController {

    private final BankReconciliationService service;
    
    public BankReconciliationController(BankReconciliationService service) {this.service=service;}
    // ✅ DASHBOARD SUMMARY API
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        return ResponseEntity.ok(service.getSummary(from, to));
    }

    // ✅ FINALIZE BUTTON API (used by frontend button)
    @PostMapping("/finalize")
    public ResponseEntity<?> finalize(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        return ResponseEntity.ok(service.finalizeReconciliation(from, to));
    }
}
