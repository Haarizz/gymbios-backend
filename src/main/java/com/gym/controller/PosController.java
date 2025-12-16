package com.gym.controller;

import com.gym.entity.*;
import com.gym.service.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pos")
public class PosController {

    @Autowired private PosService posService;

    // --- Products & Customers ---
    @GetMapping("/products")
    public List<PosProduct> getProducts(@RequestParam(required = false) String category) {
        return posService.getProducts(category);
    }

    @GetMapping("/customers")
    public List<PosCustomer> getCustomers() {
        return posService.getCustomers();
    }

    // --- Sessions ---
    @PostMapping("/sessions")
    public PosSession startSession(@RequestBody Map<String, Double> payload) {
        return posService.startSession(payload.get("openingCash"));
    }

    @PostMapping("/sessions/{id}/close")
    public PosSession closeSession(@PathVariable Long id, @RequestBody Map<String, Double> payload) {
        return posService.closeSession(id, payload.get("closingCash"));
    }

    // --- Sales ---
    @PostMapping("/sales")
    public PosSale createSale(@RequestBody PosSale sale) {
        return posService.createSale(sale);
    }

    // --- Held Orders ---
    @PostMapping("/held")
    public PosHeldOrder holdOrder(@RequestBody PosHeldOrder order) {
        return posService.holdOrder(order);
    }

    @GetMapping("/held")
    public List<PosHeldOrder> getHeldOrders() {
        return posService.getHeldOrders();
    }

    @DeleteMapping("/held/{id}")
    public ResponseEntity<?> deleteHeldOrder(@PathVariable Long id) {
        posService.deleteHeldOrder(id);
        return ResponseEntity.ok().build();
    }

    // --- Cash Movements ---
    @PostMapping("/cash-movements")
    public PosCashMovement recordCashMovement(@RequestBody PosCashMovement movement) {
        return posService.recordCashMovement(movement);
    }
}