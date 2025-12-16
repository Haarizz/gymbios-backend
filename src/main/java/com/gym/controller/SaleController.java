package com.gym.controller;

import com.gym.entity.Sale;
import com.gym.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public Sale create(@RequestBody Sale sale) {
        return service.createSale(sale);
    }

    @GetMapping
    public List<Sale> getAll() {
        return service.getAllSales();
    }
}
