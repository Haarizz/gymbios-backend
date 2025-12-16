package com.gym.controller;

import com.gym.entity.ProductionBatch;
import com.gym.entity.Recipe;
import com.gym.repository.ProductionBatchRepository;
import com.gym.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Uses a small inner request class, not a separate DTO file.
 */
@RestController
@RequestMapping("/api/production-batches")

public class ProductionBatchController {

    @Autowired
    private ProductionBatchRepository batchRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<ProductionBatch> getAllBatches() {
        return batchRepository.findAll();
    }

    @PostMapping
    public ProductionBatch createBatch(@RequestBody CreateBatchRequest request) {

        Recipe recipe = null;
        if (request.getRecipeId() != null) {
            recipe = recipeRepository.findById(request.getRecipeId()).orElse(null);
        }

        ProductionBatch batch = new ProductionBatch();
        batch.setRecipe(recipe);

        batch.setBatchSize(request.getBatchSize());
        batch.setProductionDate(request.getProductionDate());
        batch.setExpiryDate(request.getExpiryDate());
        batch.setStaff(request.getStaff());
        batch.setNotes(request.getNotes());

        // generate batch number like BATCH-2025-001
        long count = batchRepository.count() + 1;
        String number = String.format("BATCH-%d-%03d",
                LocalDate.now().getYear(), count);
        batch.setBatchNumber(number);

        // cost = recipe.costPerUnit * batchSize
        if (recipe != null && recipe.getCostPerUnit() != null
                && request.getBatchSize() != null) {
            batch.setCost(recipe.getCostPerUnit() * request.getBatchSize());
        } else {
            batch.setCost(0.0);
        }

        batch.setStatus("in-progress"); // default

        return batchRepository.save(batch);
    }

    // ----- inner request class, NOT a separate DTO file -----
    public static class CreateBatchRequest {
        private Long recipeId;
        private Integer batchSize;
        private LocalDate productionDate;
        private LocalDate expiryDate;
        private String staff;
        private String notes;

        public Long getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(Long recipeId) {
            this.recipeId = recipeId;
        }

        public Integer getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(Integer batchSize) {
            this.batchSize = batchSize;
        }

        public LocalDate getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(LocalDate productionDate) {
            this.productionDate = productionDate;
        }

        public LocalDate getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(LocalDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getStaff() {
            return staff;
        }

        public void setStaff(String staff) {
            this.staff = staff;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }
}
