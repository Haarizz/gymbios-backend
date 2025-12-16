package com.gym.controller;

import com.gym.entity.AssetTransaction;
import com.gym.service.AssetTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asset-transactions")
public class AssetTransactionController {

    @Autowired
    private AssetTransactionService service;

    @GetMapping
    public List<AssetTransaction> getAll() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetTransaction> getById(@PathVariable Long id) {
        AssetTransaction txn = service.getTransactionById(id);
        return txn != null ? ResponseEntity.ok(txn) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public AssetTransaction create(@RequestBody AssetTransaction txn) {
        return service.createTransaction(txn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetTransaction> update(@PathVariable Long id, @RequestBody AssetTransaction txn) {
        AssetTransaction updated = service.updateTransaction(id, txn);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    // CHANGED: @PatchMapping -> @PutMapping to match your Security Config
    @PutMapping("/{id}/status")
    public ResponseEntity<AssetTransaction> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        AssetTransaction updated = service.updateStatus(id, status);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}