package com.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.gym.entity.SalaryPayment;
import com.gym.entity.Staff;
import com.gym.repository.SalaryPaymentRepository;
import com.gym.repository.StaffRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping({"/salary", "/api/salary"})
public class SalaryPaymentController {

    @Autowired
    private SalaryPaymentRepository salaryRepo;

    @Autowired
    private StaffRepository staffRepo;

    // Return staff list (for frontend employee list)
    @GetMapping("/employees")
    public List<Staff> getEmployees() {
        return staffRepo.findAll();
    }

    // Return all salary payments (for summary / recent)
    @GetMapping("/payments")
    public List<SalaryPayment> getAllPayments() {
        return salaryRepo.findAll();
    }

    // Return recent payments (latest 10)
    @GetMapping("/payments/recent")
    public List<SalaryPayment> getRecentPayments() {
        return salaryRepo.findAll()
                .stream()
                .sorted((a, b) -> Long.compare(b.getId(), a.getId()))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Create a salary payment and update staff.status to "Paid"
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody SalaryPayment s) {

        // if staff exists, fill fields on payment and update staff status
        if (s.getStaffId() != null) {
            Staff st = staffRepo.findById(s.getStaffId()).orElse(null);
            if (st != null) {
                s.setStaffName((st.getFirstname() != null ? st.getFirstname() : "") + " " + (st.getLastname() != null ? st.getLastname() : ""));
                s.setEmployeeId(st.getEmployeeId());
                s.setDepartment(st.getDepartment());
                s.setRole(st.getRole());
                s.setBranch(st.getBranch());

                // set default amounts from staff if amount not provided
                if (s.getAmount() == null || s.getAmount() == 0.0) {
                    double base = st.getBaseSalary() != null ? st.getBaseSalary() : 0.0;
                    double allow = st.getAllowances() != null ? st.getAllowances() : 0.0;
                    double ded = st.getDeductions() != null ? st.getDeductions() : 0.0;
                    s.setAmount(base + allow - ded);
                }

                // mark staff as Paid
                st.setStatus("Paid");
                staffRepo.save(st);
            }
        }

        if (s.getStatus() == null) s.setStatus("Paid");
        SalaryPayment saved = salaryRepo.save(s);
        return ResponseEntity.ok(saved);
    }
}
