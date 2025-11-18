package com.gym.controller;

import com.gym.entity.ClassSchedule;
import com.gym.service.ClassScheduleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/class-schedules")
public class ClassScheduleController {
    private final ClassScheduleService service;

    public ClassScheduleController(ClassScheduleService service){
        this.service = service;
    }

    @GetMapping
    public List<ClassSchedule> list(){ return service.list(); }

    @PostMapping
    public ClassSchedule create(@RequestBody ClassSchedule s){ return service.create(s); }

    @PutMapping("/{id}")
    public ClassSchedule update(@PathVariable Integer id, @RequestBody ClassSchedule s){
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){ service.delete(id); }
}
