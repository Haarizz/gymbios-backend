package com.gym.controller;

import com.gym.entity.Budget;
import com.gym.repository.BudgetRepository;
import com.gym.service.BudgetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/budgeting")
public class BudgetingController {

    private final BudgetingService service;
    private final BudgetRepository repo;

    public BudgetingController(BudgetingService service, BudgetRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    // --------------------------------------------
    // 1) OVERVIEW METRICS
    // --------------------------------------------
    @GetMapping("/overview")
    public ResponseEntity<?> overview() {
        Map<String,Object> m = new HashMap<>();

        BigDecimal totalBudget = service.totalBudget();
        BigDecimal totalSpent = service.totalSpent();
        BigDecimal remaining = totalBudget.subtract(totalSpent);

        m.put("totalBudget", totalBudget);
        m.put("totalSpent", totalSpent);
        m.put("remaining", remaining);
        m.put("rulesCount", service.rulesCount());

        return ResponseEntity.ok(m);
    }

    // --------------------------------------------
    // 2) BUDGET MASTER LIST
    // --------------------------------------------
    @GetMapping("/master")
    public ResponseEntity<?> master() {

        List<Budget> list = repo.findAll();
        List<Map<String,Object>> out = new ArrayList<>();

        for (Budget b : list) {
            Map<String,Object> m = new LinkedHashMap<>();
            m.put("id", b.getId());
            m.put("code", b.getCode());
            m.put("category", b.getCategory());
            m.put("department", b.getDepartment());
            m.put("branch", b.getBranch());
            m.put("responsible", b.getResponsible());
            m.put("type", b.getType());
            m.put("status", b.getStatus());
            m.put("notes", b.getNotes());
            m.put("budget", b.getAmount());

            BigDecimal spent = service.getTotalSpentForBudget(b.getId());
            m.put("spent", spent);
            m.put("remaining", b.getAmount().subtract(spent));

            out.add(m);
        }

        return ResponseEntity.ok(out);
    }

    // --------------------------------------------
    // 3) CREATE BUDGET
    // --------------------------------------------
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String,Object> p) {
        try {
            Budget b = new Budget();

            b.setCode((String)p.getOrDefault("code", ""));
            b.setCategory((String)p.getOrDefault("category", ""));
            b.setDepartment((String)p.getOrDefault("department", ""));
            b.setBranch((String)p.getOrDefault("branch", ""));
            b.setResponsible((String)p.getOrDefault("responsible", ""));
            b.setType((String)p.getOrDefault("type", ""));
            b.setStatus((String)p.getOrDefault("status", "Active"));
            b.setNotes((String)p.getOrDefault("notes", ""));

            if (p.containsKey("amount"))
                b.setAmount(new BigDecimal(p.get("amount").toString()));

            if (p.containsKey("alertThresholdPct"))
                b.setAlertThresholdPct(Integer.parseInt(p.get("alertThresholdPct").toString()));

            Budget saved = repo.save(b);

            return ResponseEntity.ok(Map.of("id", saved.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    // --------------------------------------------
    // 4) UPDATE BUDGET
    // --------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String,Object> p) {

        Optional<Budget> opt = repo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Budget b = opt.get();

        if (p.containsKey("category")) b.setCategory((String)p.get("category"));
        if (p.containsKey("department")) b.setDepartment((String)p.get("department"));
        if (p.containsKey("branch")) b.setBranch((String)p.get("branch"));
        if (p.containsKey("responsible")) b.setResponsible((String)p.get("responsible"));
        if (p.containsKey("type")) b.setType((String)p.get("type"));
        if (p.containsKey("status")) b.setStatus((String)p.get("status"));
        if (p.containsKey("notes")) b.setNotes((String)p.get("notes"));

        if (p.containsKey("amount"))
            b.setAmount(new BigDecimal(p.get("amount").toString()));

        if (p.containsKey("alertThresholdPct"))
            b.setAlertThresholdPct(Integer.parseInt(p.get("alertThresholdPct").toString()));

        repo.save(b);
        return ResponseEntity.ok(Map.of("message", "updated"));
    }

    // --------------------------------------------
    // 5) BvA ANALYTICS (SIMPLE)
    // --------------------------------------------
    @GetMapping("/analytics/bva")
    public ResponseEntity<?> bva() {

        List<Map<String, Object>> series = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (int i = 5; i >= 0; i--) {
            LocalDate month = now.minusMonths(i).withDayOfMonth(1);

            BigDecimal totalBudget = service.totalBudget();
            BigDecimal totalSpent = service.totalSpent(month);

            Map<String,Object> point = new HashMap<>();
            point.put("month", month.toString());
            point.put("budget", totalBudget);
            point.put("actual", totalSpent);

            series.add(point);
        }

        return ResponseEntity.ok(series);
    }
}
