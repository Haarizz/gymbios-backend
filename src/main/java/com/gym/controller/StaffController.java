package com.gym.controller;

import com.gym.entity.Staff;
import com.gym.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    // --------------------------------
    // GET ALL STAFF
    // --------------------------------
    @GetMapping
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // --------------------------------
    // GET STAFF BY ID
    // --------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        return staffRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --------------------------------
    // CREATE STAFF
    // --------------------------------
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff saved = staffRepository.save(staff);
        return ResponseEntity.ok(saved);
    }

    // --------------------------------
    // UPDATE STAFF
    // --------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(
            @PathVariable Long id,
            @RequestBody Staff updatedStaff) {

        return staffRepository.findById(id)
                .map(existing -> {

                    existing.setFirstname(updatedStaff.getFirstname());
                    existing.setLastname(updatedStaff.getLastname());
                    existing.setRole(updatedStaff.getRole());
                    existing.setDepartment(updatedStaff.getDepartment());
                    existing.setBranch(updatedStaff.getBranch());
                    existing.setPhone(updatedStaff.getPhone());
                    existing.setEmail(updatedStaff.getEmail());
                    existing.setAddress(updatedStaff.getAddress());
                    existing.setMonthlyTarget(updatedStaff.getMonthlyTarget());
                    existing.setTarget(updatedStaff.getTarget());
                    existing.setEmployeeId(updatedStaff.getEmployeeId());

                    Staff saved = staffRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // --------------------------------
    // DELETE STAFF
    // --------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStaff(@PathVariable Long id) {
        return staffRepository.findById(id)
                .map(staff -> {
                    staffRepository.delete(staff);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
