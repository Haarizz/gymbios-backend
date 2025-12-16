package com.gym.controller;

import com.gym.entity.Recipe;
import com.gym.entity.RecipeIngredient;
import com.gym.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/production-recipes")
public class ProductionRecipeController {

    private final RecipeRepository recipeRepository;

    public ProductionRecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // ---------------------------------------------------------------------
    // BASIC CRUD
    // ---------------------------------------------------------------------

    @GetMapping
    public List<Recipe> getAllRecipes() {
        // This should return all existing recipes from your DB
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        // Ensure this is always treated as NEW
        recipe.setId(null);

        // If you have ingredients mapping, keep the back-reference clean
        if (recipe.getIngredients() != null) {
            for (RecipeIngredient ing : recipe.getIngredients()) {
                ing.setId(null);       // new ingredient row
                ing.setRecipe(recipe); // back-reference
            }
        }

        Recipe saved = recipeRepository.save(recipe);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(
            @PathVariable Long id,
            @RequestBody Recipe incoming
    ) {
        Recipe existing = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));

        // copy simple fields
        existing.setName(incoming.getName());
        existing.setDescription(incoming.getDescription());
        existing.setCategory(incoming.getCategory());
        existing.setServingSize(incoming.getServingSize());
        existing.setPrepTime(incoming.getPrepTime());
        existing.setCookTime(incoming.getCookTime());
        existing.setYieldValue(incoming.getYieldValue());
        existing.setPackagingCost(incoming.getPackagingCost());
        existing.setLaborCost(incoming.getLaborCost());
        existing.setSellingPrice(incoming.getSellingPrice());
        existing.setStatus(incoming.getStatus());
        existing.setNutrition(incoming.getNutrition());
        existing.setCostPerUnit(incoming.getCostPerUnit());
        existing.setProfitMargin(incoming.getProfitMargin());
        existing.setIngredientsCount(incoming.getIngredientsCount());

        // OPTIONAL: if your ingredients mapping is working fine, keep this.
        // If ingredients cause issues, you can comment this block out.
        List<RecipeIngredient> newIngredients = new ArrayList<>();
        if (incoming.getIngredients() != null) {
            for (RecipeIngredient ing : incoming.getIngredients()) {
                RecipeIngredient copy = new RecipeIngredient();
                copy.setName(ing.getName());
                copy.setQty(ing.getQty());
                copy.setUnit(ing.getUnit());
                copy.setCostPerUnit(ing.getCostPerUnit());
                copy.setTotal(ing.getTotal());
                copy.setRecipe(existing);    // back-reference
                newIngredients.add(copy);
            }
        }
        existing.setIngredients(newIngredients);

        Recipe saved = recipeRepository.save(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------------------------------------------------
    // SIMPLE DUPLICATE – easiest, safe with your DB
    // POST /api/production-recipes/{id}/duplicate
    // ---------------------------------------------------------------------

    @PostMapping("/{id}/duplicate")
    public ResponseEntity<Recipe> duplicateRecipe(@PathVariable Long id) {
        Recipe original = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));

        // 👉 Simple clone of the main row (no ingredients duplication)
        Recipe copy = new Recipe();

        // Change name so you can easily see it’s a copy
        copy.setName(original.getName() + " (Copy)");

        copy.setDescription(original.getDescription());
        copy.setCategory(original.getCategory());
        copy.setServingSize(original.getServingSize());
        copy.setPrepTime(original.getPrepTime());
        copy.setCookTime(original.getCookTime());
        copy.setYieldValue(original.getYieldValue());
        copy.setPackagingCost(original.getPackagingCost());
        copy.setLaborCost(original.getLaborCost());
        copy.setSellingPrice(original.getSellingPrice());
        copy.setStatus(original.getStatus());
        copy.setNutrition(original.getNutrition());
        copy.setCostPerUnit(original.getCostPerUnit());
        copy.setProfitMargin(original.getProfitMargin());
        copy.setIngredientsCount(original.getIngredientsCount());

        // ❗ Easiest option: do NOT copy ingredient rows.
        // This avoids all join / FK / cascade problems.
        // You can later open the copy and add ingredients manually.
        copy.setIngredients(null);

        Recipe savedCopy = recipeRepository.save(copy);
        return ResponseEntity.ok(savedCopy);
    }
}
