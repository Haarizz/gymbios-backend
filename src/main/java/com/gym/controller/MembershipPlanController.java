package com.gym.controller;

import com.gym.entity.MembershipPlan;
import com.gym.repository.MembershipPlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanRepository repo;

    @PostMapping
    public MembershipPlan create(@RequestBody MembershipPlan plan) {
        return repo.save(plan);
    }

    @GetMapping
    public List<MembershipPlan> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public MembershipPlan getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public MembershipPlan update(@PathVariable Long id, @RequestBody MembershipPlan updated) {
        updated.setId(id);
        return repo.save(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}


