package com.gym.controller;

import com.gym.entity.WastageReturn;
import com.gym.service.WastageReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<WastageReturn> getById(@PathVariable Long id) {
        WastageReturn wr = service.getById(id);
        if (wr == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wr);
    }

    @PostMapping
    public WastageReturn create(@RequestBody WastageReturn wr) {
        if (wr.getStatus() == null || wr.getStatus().isEmpty()) {
            wr.setStatus("Pending Approval");
        }
        // ID is implicitly null on POST.
        return service.save(wr);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WastageReturn> update(@PathVariable Long id, @RequestBody WastageReturn wr) {
        WastageReturn existing = service.getById(id);
        
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Update fields from the request body (wr)
        // ✅ FIX: Ensure existing voucher number is updated from the request body
        existing.setVoucherNumber(wr.getVoucherNumber()); 
        existing.setVoucherType(wr.getVoucherType());
        existing.setDate(wr.getDate());
        existing.setReason(wr.getReason());
        existing.setLocation(wr.getLocation());
        existing.setProducts(wr.getProducts());
        existing.setNotes(wr.getNotes());
        existing.setStatus(wr.getStatus());
        existing.setTotalValue(wr.getTotalValue());
        existing.setPartyType(wr.getPartyType());

        // Save the updated entity
        return ResponseEntity.ok(service.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}