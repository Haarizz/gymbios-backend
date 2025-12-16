package com.gym.controller;

import com.gym.entity.SalaryAdvance;
import com.gym.repository.SalaryAdvanceRepository;
import com.gym.repository.StaffRepository;
import com.gym.entity.Staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salary/advances")
@CrossOrigin
public class SalaryAdvanceController {

    @Autowired
    private SalaryAdvanceRepository repo;

    @Autowired
    private StaffRepository staffRepo; // optional: to link advances to staff/employee

    // GET /api/salary/advances
    @GetMapping
    public List<SalaryAdvance> listAll() {
        return repo.findAll();
    }

    // GET /api/salary/advances/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SalaryAdvance> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /api/salary/advances
    @PostMapping
    public ResponseEntity<SalaryAdvance> create(@RequestBody SalaryAdvance payload) {
        // set defaults
        if (payload.getStatus() == null) payload.setStatus("Pending Approval");
        if (payload.getRequestedAmount() == null) payload.setRequestedAmount(0.0);
        // try to bind staffId from employeeId if possible
        if (payload.getEmployeeId() != null && (payload.getStaffId() == null || payload.getStaffId() == 0L)) {
            staffRepo.findByEmployeeId(payload.getEmployeeId()).ifPresent(s -> payload.setStaffId(s.getId()));
        }
        SalaryAdvance saved = repo.save(payload);
        return ResponseEntity.ok(saved);
    }

    // PUT /api/salary/advances/{id} - generic update
    @PutMapping("/{id}")
    public ResponseEntity<SalaryAdvance> update(@PathVariable Long id, @RequestBody SalaryAdvance payload) {
        return repo.findById(id).map(existing -> {
            // copy fields you want to allow updating
            existing.setApprovedAmount(payload.getApprovedAmount() == null ? existing.getApprovedAmount() : payload.getApprovedAmount());
            existing.setPaidAmount(payload.getPaidAmount() == null ? existing.getPaidAmount() : payload.getPaidAmount());
            existing.setInstallments(payload.getInstallments() == null ? existing.getInstallments() : payload.getInstallments());
            existing.setInstallmentAmount(payload.getInstallmentAmount() == null ? existing.getInstallmentAmount() : payload.getInstallmentAmount());
            existing.setDeductionMode(payload.getDeductionMode() == null ? existing.getDeductionMode() : payload.getDeductionMode());
            existing.setAutoDeduct(payload.getAutoDeduct() == null ? existing.getAutoDeduct() : payload.getAutoDeduct());
            if (payload.getStatus() != null) existing.setStatus(payload.getStatus());
            if (payload.getApprovedOn() != null) existing.setApprovedOn(payload.getApprovedOn());
            if (payload.getRemarks() != null) existing.setRemarks(payload.getRemarks());
            SalaryAdvance saved = repo.save(existing);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    // PUT /api/salary/advances/{id}/approve - special approval operation called by front-end
    @PutMapping("/{id}/approve")
    public ResponseEntity<SalaryAdvance> approve(@PathVariable Long id, @RequestBody ApproveRequest body) {
        Optional<SalaryAdvance> opt = repo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        SalaryAdvance adv = opt.get();

        // validate approved amount
        if (body.getApprovedAmount() == null || body.getApprovedAmount() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        adv.setApprovedAmount(body.getApprovedAmount());
        adv.setInstallments(body.getInstallments() == null ? 1 : body.getInstallments());
        adv.setInstallmentAmount(body.getInstallments() != null && body.getInstallments() > 0 ? (body.getApprovedAmount() / body.getInstallments()) : body.getApprovedAmount());
        adv.setDeductionMode(body.getDeductionMode());
        adv.setAutoDeduct(body.getAutoDeduct() == null ? Boolean.TRUE : body.getAutoDeduct());
        adv.setStatus("Active");
        adv.setApprovedOn(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        if (body.getNotes() != null) adv.setRemarks(body.getNotes());

        // persist
        SalaryAdvance saved = repo.save(adv);

        // optionally update staff status if you want:
        if (adv.getStaffId() != null) {
            staffRepo.findById(adv.getStaffId()).ifPresent(s -> {
                s.setStatus("HasAdvance");
                staffRepo.save(s);
            });
        }

        return ResponseEntity.ok(saved);
    }

    // DELETE /api/salary/advances/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repo.findById(id).map(a -> {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // small DTO for approve payload
    public static class ApproveRequest {
        private Double approvedAmount;
        private Integer installments;
        private String deductionMode;
        private Boolean autoDeduct;
        private String startMonth;
        private String notes;

        public Double getApprovedAmount() { return approvedAmount; }
        public void setApprovedAmount(Double approvedAmount) { this.approvedAmount = approvedAmount; }

        public Integer getInstallments() { return installments; }
        public void setInstallments(Integer installments) { this.installments = installments; }

        public String getDeductionMode() { return deductionMode; }
        public void setDeductionMode(String deductionMode) { this.deductionMode = deductionMode; }

        public Boolean getAutoDeduct() { return autoDeduct; }
        public void setAutoDeduct(Boolean autoDeduct) { this.autoDeduct = autoDeduct; }

        public String getStartMonth() { return startMonth; }
        public void setStartMonth(String startMonth) { this.startMonth = startMonth; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
    }
}
