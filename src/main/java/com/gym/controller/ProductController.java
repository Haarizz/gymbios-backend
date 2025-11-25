package com.gym.controller;


import org.springframework.web.bind.annotation.*;

import com.gym.entity.Product;
import com.gym.service.ProductService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET list + search
    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String q) {
        return service.getAll(q);
    }

    // GET one
    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product data) {
        return service.update(id, data);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Product deleted");
    }

    // STATS (Dashboard cards)
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return service.getStats();
    }

    // CATEGORY COUNTS (Category cards)
    @GetMapping("/categories")
    public List<Map<String, Object>> getCategories() {
        return service.getCategoryCounts();
    }
}
