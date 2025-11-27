package com.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.entity.PurchaseOrder;
import com.gym.service.PurchaseOrderService;


@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

    @PostMapping
    public PurchaseOrder create(@RequestBody PurchaseOrder po) {
        return service.createPO(po);
    }

    @PutMapping("/{id}")
    public PurchaseOrder update(@PathVariable Long id, @RequestBody PurchaseOrder po) {
        return service.updatePO(id, po);
    }

    @GetMapping
    public List<PurchaseOrder> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PurchaseOrder getOne(@PathVariable Long id) {
        return service.getOne(id);
    }
}
