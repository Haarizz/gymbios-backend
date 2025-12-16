package com.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.gym.entity.Staff;
import com.gym.repository.StaffRepository;

@RestController
@CrossOrigin
@RequestMapping({"/staff", "/api/staff"})
public class SalaryStaffController {

    @Autowired
    private StaffRepository staffRepo;

    // Update staff — used by Set Salary modal (PUT /staff/{id} or /api/staff/{id})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStaffSalary(@PathVariable Long id, @RequestBody Staff payload) {
        return staffRepo.findById(id).map(existing -> {
            // Update only salary-related fields and status if provided; also allow other small fields
            if (payload.getBaseSalary() != null) existing.setBaseSalary(payload.getBaseSalary());
            if (payload.getAllowances() != null) existing.setAllowances(payload.getAllowances());
            if (payload.getDeductions() != null) existing.setDeductions(payload.getDeductions());
            if (payload.getStatus() != null) existing.setStatus(payload.getStatus());

            // also allow updating basic profile fields safely (optional)
            if (payload.getFirstname() != null) existing.setFirstname(payload.getFirstname());
            if (payload.getLastname() != null) existing.setLastname(payload.getLastname());
            if (payload.getEmployeeId() != null) existing.setEmployeeId(payload.getEmployeeId());
            if (payload.getRole() != null) existing.setRole(payload.getRole());
            if (payload.getDepartment() != null) existing.setDepartment(payload.getDepartment());

            Staff saved = staffRepo.save(existing);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }
}
