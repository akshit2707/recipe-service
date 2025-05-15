package com.api.recipe.service;

import com.api.recipe.dto.response.RecipeResponse;

public interface RecipeService {
  RecipeResponse fetchAllRecipes();

  RecipeResponse getRecipeById(String id);
}
