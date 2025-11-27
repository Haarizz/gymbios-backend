package com.gym.controller;

import com.gym.entity.WastageReturn;
import com.gym.service.WastageReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wastage-return")
public class WastageReturnController {

    @Autowired
    private WastageReturnService service;

    @GetMapping
    public List<WastageReturn> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WastageReturn getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public WastageReturn create(@RequestBody WastageReturn wr) {
        wr.setStatus("Pending");
        return service.save(wr);
    }

    @PutMapping("/{id}")
    public WastageReturn update(@PathVariable Long id, @RequestBody WastageReturn wr) {
        wr.setId(id);
        return service.save(wr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
