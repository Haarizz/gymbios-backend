package com.gym.controller;

import com.gym.dto.PayrollRequest;
import com.gym.entity.PayrollCycle;
import com.gym.service.PayrollService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService service;

    // Generate payroll (POST)
    @PostMapping("/generate")
    public PayrollCycle generatePayroll(@RequestBody PayrollRequest request) {
        return service.generatePayroll(request.getMonth(), request.getYear());
    }

    // Get all payroll history
    @GetMapping("/history")
    public List<PayrollCycle> history() {
        return service.getAllCycles();
    }

    // Get pending payrolls
    @GetMapping("/pending")
    public List<PayrollCycle> pending() {
        return service.getPendingCycles();
    }

    // Approve payroll
    @PutMapping("/approve/{id}")
    public PayrollCycle approve(@PathVariable Long id) {
        return service.approvePayroll(id);
    }
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard() {
        return service.getDashboardData();
    }

}
