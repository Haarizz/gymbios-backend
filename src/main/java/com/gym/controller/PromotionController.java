package com.gym.controller;

import com.gym.entity.Promotion;
import com.gym.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    // GET ALL PROMOTIONS (for the table/grid view)
    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    // GET PROMOTION BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long id) {
        return promotionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE A NEW PROMOTION
    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        // Set initial status and tracking data if not provided
        if (promotion.getStatus() == null) {
            promotion.setStatus("Scheduled"); // Default status from UI
        }
        if (promotion.getStartDate() != null && promotion.getStartDate().isBefore(LocalDate.now())) {
             promotion.setStatus("Active"); // Mark as active if start date is in the past
        }
        
        // This is a minimal save. A real service layer should handle complex logic (e.g., checking code uniqueness).
        Promotion saved = promotionRepository.save(promotion);
        return ResponseEntity.ok(saved);
    }

    // UPDATE PROMOTION
    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(
            @PathVariable Long id,
            @RequestBody Promotion updatedPromotion) {

        return promotionRepository.findById(id)
                .map(existingPromotion -> {
                    // Manually map fields to persist changes. Use service layer for DTO mapping in production.
                    existingPromotion.setPromotionName(updatedPromotion.getPromotionName());
                    existingPromotion.setPromotionCode(updatedPromotion.getPromotionCode());
                    existingPromotion.setDiscountValue(updatedPromotion.getDiscountValue());
                    existingPromotion.setStatus(updatedPromotion.getStatus());
                    // ... set all other updatable fields ...
                    
                    Promotion saved = promotionRepository.save(existingPromotion);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE PROMOTION
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePromotion(@PathVariable Long id) {
        return promotionRepository.findById(id)
                .map(promotion -> {
                    promotionRepository.delete(promotion);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}