// package com.gym.controller;
package com.gym.controller;

import com.gym.entity.Staff;
import com.gym.entity.SalaryPayment;
import com.gym.repository.StaffRepository;
import com.gym.repository.SalaryPaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/salary")
@CrossOrigin
public class SalaryController {

    @Autowired
    private StaffRepository staffRepo;

    @Autowired
    private SalaryPaymentRepository paymentRepo;

    // 1) Get all employees for salary UI
    @GetMapping("/employees")
    public List<Staff> getEmployees() {
        return staffRepo.findAll();
    }

    // 2) Update staff salary / status (used by "Set Salary" modal)
    @PutMapping("/employees/{id}")
    public ResponseEntity<Staff> updateStaffSalary(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return staffRepo.findById(id).map(st -> {
            if (payload.containsKey("baseSalary")) {
                st.setBaseSalary(toDouble(payload.get("baseSalary")));
            }
            if (payload.containsKey("allowances")) {
                st.setAllowances(toDouble(payload.get("allowances")));
            }
            if (payload.containsKey("deductions")) {
                st.setDeductions(toDouble(payload.get("deductions")));
            }
            if (payload.containsKey("status")) {
                st.setStatus(String.valueOf(payload.get("status")));
            }
            Staff saved = staffRepo.save(st);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 3) Create single salary payment
    @PostMapping("/payments")
    public ResponseEntity<SalaryPayment> createPayment(@RequestBody Map<String,Object> body) {
        SalaryPayment p = new SalaryPayment();

        // staff lookup
        Long staffId = body.containsKey("staffId") ? toLong(body.get("staffId")) : null;
        if (staffId == null && body.containsKey("employeeId")) {
            String empId = String.valueOf(body.get("employeeId"));
            staffId = staffRepo.findByEmployeeId(empId).map(Staff::getId).orElse(null);
        }
        if (staffId != null) {
            Staff s = staffRepo.findById(staffId).orElse(null);
            if (s != null) {
                p.setStaffId(s.getId());
                p.setStaffName((s.getFirstname() == null ? "" : s.getFirstname()) + " " + (s.getLastname() == null ? "" : s.getLastname()));
                p.setEmployeeId(s.getEmployeeId());
                p.setDepartment(s.getDepartment());
                p.setRole(s.getRole());
                p.setBranch(s.getBranch());
            }
        } else {
            p.setEmployeeId(body.containsKey("employeeId") ? String.valueOf(body.get("employeeId")) : null);
            p.setStaffName(body.containsKey("staffName") ? String.valueOf(body.get("staffName")) : null);
        }

        // amount fields
        double bank = toDouble(body.getOrDefault("bankAmount", 0));
        double cash = toDouble(body.getOrDefault("cashAmount", 0));
        double amount = toDouble(body.getOrDefault("amount", bank + cash));

        p.setAmount(amount);
        p.setBankAmount(bank);
        p.setCashAmount(cash);

        String mode = String.valueOf(body.getOrDefault("paymentMode", (bank>0 && cash>0) ? "Bank+Cash" : (bank>0 ? "Bank" : "Cash")));
        p.setPaymentMode(mode);

        p.setPaymentDate(body.containsKey("paymentDate") ? String.valueOf(body.get("paymentDate")) : LocalDate.now().toString());
        p.setMonth(body.containsKey("month") ? String.valueOf(body.get("month")) : LocalDate.now().getMonth().name() + " " + LocalDate.now().getYear());
        p.setStatus(body.containsKey("status") ? String.valueOf(body.get("status")) : "Paid");
        p.setRemarks(body.containsKey("remarks") ? String.valueOf(body.get("remarks")) : "");

        // Validation: don't save zero amounts
        if (p.getAmount() == null || p.getAmount() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        SalaryPayment saved = paymentRepo.save(p);

        // Optionally mark staff as Paid
        if (p.getStaffId() != null && "Paid".equalsIgnoreCase(p.getStatus())) {
            staffRepo.findById(p.getStaffId()).ifPresent(s -> {
                s.setStatus("Paid");
                staffRepo.save(s);
            });
        }

        return ResponseEntity.ok(saved);
    }

    // 4) Bulk payments (employeeIds may be codes like EMP001 or numeric staff ids)
    @PostMapping("/payments/bulk")
    public ResponseEntity<?> bulkPayments(@RequestBody Map<String,Object> body) {
        List<Object> empIdsObj = (List<Object>) body.getOrDefault("employeeIds", body.getOrDefault("staffIds", Collections.emptyList()));
        if (empIdsObj == null || empIdsObj.isEmpty()) return ResponseEntity.badRequest().body("employeeIds required");

        List<SalaryPayment> created = new ArrayList<>();
        for (Object o : empIdsObj) {
            Long staffId = null;
            if (o instanceof Number) {
                staffId = ((Number)o).longValue();
            } else {
                String s = String.valueOf(o);
                if (s.startsWith("EMP")) {
                    staffId = staffRepo.findByEmployeeId(s).map(Staff::getId).orElse(null);
                } else {
                    try { staffId = Long.parseLong(s); } catch(Exception ex) {}
                }
            }
            if (staffId == null) continue;
            Staff st = staffRepo.findById(staffId).orElse(null);
            if (st == null) continue;
            double base = st.getBaseSalary() == null ? 0 : st.getBaseSalary();
            double allowances = st.getAllowances() == null ? 0 : st.getAllowances();
            double deductions = st.getDeductions() == null ? 0 : st.getDeductions();
            double net = base + allowances - deductions;
            if (net <= 0) continue;

            SalaryPayment p = new SalaryPayment();
            p.setStaffId(st.getId());
            p.setStaffName((st.getFirstname()==null?"":st.getFirstname()) + " " + (st.getLastname()==null?"":st.getLastname()));
            p.setEmployeeId(st.getEmployeeId());
            p.setAmount(net);
            p.setBankAmount(net);
            p.setCashAmount(0.0);
            p.setPaymentMode("Bank");
            p.setPaymentDate(LocalDate.now().toString());
            p.setMonth(LocalDate.now().getMonth().name() + " " + LocalDate.now().getYear());
            p.setStatus("Paid");
            p.setRemarks("Bulk payment");
            created.add(paymentRepo.save(p));
            st.setStatus("Paid");
            staffRepo.save(st);
        }
        return ResponseEntity.ok(created);
    }

    // 5) Recent payments
    @GetMapping("/payments/recent")
    public List<SalaryPayment> recentPayments() {
        try {
            return paymentRepo.findTop20ByOrderByIdDesc();
        } catch (Exception e) {
            return paymentRepo.findAll();
        }
    }

    // --- Helpers
    private Double toDouble(Object v) {
        if (v == null) return 0.0;
        if (v instanceof Number) return ((Number)v).doubleValue();
        try { return Double.parseDouble(String.valueOf(v)); }
        catch (Exception e) { return 0.0; }
    }
    private Long toLong(Object v) {
        if (v == null) return null;
        if (v instanceof Number) return ((Number)v).longValue();
        try { return Long.parseLong(String.valueOf(v)); } catch (Exception e) { return null; }
    }
}
