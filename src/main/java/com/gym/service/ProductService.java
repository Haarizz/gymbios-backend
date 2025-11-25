package com.gym.service;

import org.springframework.stereotype.Service;

import com.gym.entity.Product;
import com.gym.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll(String q) {
        if (q == null || q.isEmpty()) return repo.findAll();
        return repo.search(q);
    }

    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public Product update(Long id, Product newData) {
        Product p = getById(id);

        p.setName(newData.getName());
        p.setSku(newData.getSku());
        p.setCategory(newData.getCategory());
        p.setDescription(newData.getDescription());
        p.setPrice(newData.getPrice());
        p.setUnit(newData.getUnit());
        p.setStockQuantity(newData.getStockQuantity());

        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Dashboard stats
    public Map<String, Object> getStats() {
        Map<String, Object> map = new HashMap<>();

        long total = repo.count();
        double inventoryValue = Optional.ofNullable(repo.getInventoryValue()).orElse(0.0);
        int lowStock = Optional.ofNullable(repo.getLowStock()).orElse(0);
        int outOfStock = Optional.ofNullable(repo.getOutOfStock()).orElse(0);

        map.put("total", total);
        map.put("active", total);  // you don't use active/inactive yet
        map.put("inactive", 0);
        map.put("inventoryValue", inventoryValue);
        map.put("stockAlerts", lowStock + outOfStock);
        map.put("lowStock", lowStock);
        map.put("outOfStock", outOfStock);
        map.put("variants", 0);

        return map;
    }

    // Category counts
    public List<Map<String, Object>> getCategoryCounts() {
        List<Object[]> rows = repo.getCategoryCounts();
        List<Map<String, Object>> list = new ArrayList<>();

        for (Object[] row : rows) {
            Map<String, Object> m = new HashMap<>();
            m.put("name", row[0] != null ? row[0] : "Uncategorized");
            m.put("count", row[1]);
            list.add(m);
        }

        return list;
    }
}
