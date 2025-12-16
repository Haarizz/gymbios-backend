package com.gym.controller;

import com.gym.entity.ClassBooking;
import com.gym.service.ClassBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-bookings")
public class ClassBookingController {

    private final ClassBookingService service;

    public ClassBookingController(ClassBookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClassBooking> list(){ return service.list(); }

    @PostMapping
    public ClassBooking create(@RequestBody ClassBooking x){ return service.create(x); }

    @PutMapping("/{id}")
    public ClassBooking update(@PathVariable Integer id, @RequestBody ClassBooking x){
        return service.update(id, x);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
