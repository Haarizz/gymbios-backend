package com.gym.controller;

import com.gym.entity.Purchase;
import com.gym.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")	
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @PostMapping
    public Purchase create(@RequestBody Purchase p) {
        return service.createPurchase(p);
    }

    @PutMapping("/{id}")
    public Purchase update(@PathVariable Long id, @RequestBody Purchase p) {
        return service.updatePurchase(id, p);
    }

    @GetMapping
    public List<Purchase> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Purchase get(@PathVariable Long id) {
        return service.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
