package com.gym.controller;

import org.springframework.web.bind.annotation.*;

import com.gym.entity.TrainingClass;
import com.gym.service.TrainingClassService;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class TrainingClassController {

    private final TrainingClassService service;

    public TrainingClassController(TrainingClassService service) {
        this.service = service;
    }

    @PostMapping
    public TrainingClass create(@RequestBody TrainingClass trainingClass) {
        return service.create(trainingClass);
    }

    @GetMapping
    public List<TrainingClass> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TrainingClass getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
