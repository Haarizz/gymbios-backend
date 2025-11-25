package com.gym.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gym.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search by name, sku, category
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(p.sku) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Product> search(String q);

    @Query("SELECT SUM(p.price * p.stockQuantity) FROM Product p")
    Double getInventoryValue();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity = 0")
    Integer getOutOfStock();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity <= 5 AND p.stockQuantity > 0")
    Integer getLowStock();

    @Query("SELECT p.category AS category, COUNT(p) AS count FROM Product p GROUP BY p.category")
    List<Object[]> getCategoryCounts();
}
