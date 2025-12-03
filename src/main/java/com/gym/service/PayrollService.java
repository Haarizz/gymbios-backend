package com.gym.service;

import com.gym.entity.PayrollCycle;
import com.gym.entity.Staff;
import com.gym.repository.PayrollCycleRepository;
import com.gym.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private final PayrollCycleRepository repo;

    @Autowired
    private StaffRepository staffRepo;

    @Autowired
    public PayrollService(PayrollCycleRepository repo) {
        this.repo = repo;
    }

    public PayrollCycle generatePayroll(String month, Integer year) {

        // 1. Fetch all staff
        List<Staff> staffList = staffRepo.findAll();
        int employeeCount = staffList.size();

        // 2. Calculate salary using MONTHLY TARGET (Integer -> BigDecimal)
        BigDecimal total = staffList.stream()
                .map(st -> st.getMonthlyTarget() != null
                        ? BigDecimal.valueOf(st.getMonthlyTarget())
                        : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Create payroll cycle
        PayrollCycle p = new PayrollCycle();
        p.setMonth(month);
        p.setYear(year);
        p.setEmployeesCount(employeeCount);
        p.setTotalAmount(total);
        p.setStatus("PENDING");
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());

        // 4. Save & return
        return repo.save(p);
    }

    public List<PayrollCycle> getAllCycles() {
        return repo.findAll();
    }

    public List<PayrollCycle> getPendingCycles() {
        return repo.findByStatus("PENDING");
    }

    public List<PayrollCycle> getApprovedCycles() {
        return repo.findByStatus("APPROVED");
    }

    public PayrollCycle approvePayroll(Long id) {
        PayrollCycle p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        p.setStatus("APPROVED");
        p.setUpdatedAt(LocalDateTime.now());

        return repo.save(p);
    }

    /**
     * Dashboard aggregation used by frontend /payroll/dashboard
     * Returns a Map with keys:
     *  - totalEmployees
     *  - pendingPayrolls
     *  - approvedPayrolls
     *  - disbursedPayrolls
     *  - recentCycles (List of maps with id, month, year, employeesCount, totalAmountFormatted)
     */
    public Map<String, Object> getDashboardData() {

        long totalEmployees = staffRepo.count();
        long pending = repo.countByStatus("PENDING");
        long approved = repo.countByStatus("APPROVED");
        long disbursed = repo.countByStatus("DISBURSED");

        List<PayrollCycle> recent = repo.findTop5ByOrderByCreatedAtDesc();

        List<Map<String, Object>> cycles = recent.stream()
                .map(p -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("id", p.getId());
                    m.put("month", p.getMonth());
                    m.put("year", p.getYear());
                    m.put("employeesCount", p.getEmployeesCount());
                    m.put("totalAmountFormatted", p.getTotalAmount() != null
                            ? "AED " + p.getTotalAmount().toPlainString()
                            : "AED 0");
                    return m;
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("totalEmployees", totalEmployees);
        result.put("pendingPayrolls", pending);
        result.put("approvedPayrolls", approved);
        result.put("disbursedPayrolls", disbursed);
        result.put("recentCycles", cycles);

        return result;
    }
}
