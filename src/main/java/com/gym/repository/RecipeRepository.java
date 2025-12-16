// src/main/java/com/gym/repository/RecipeRepository.java
package com.gym.repository;

import com.gym.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
