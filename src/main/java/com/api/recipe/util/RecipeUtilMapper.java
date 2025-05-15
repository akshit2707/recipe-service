package com.api.recipe.util;

import com.api.recipe.dto.response.Recipe;
import com.api.recipe.dto.response.RecipeResponse;
import com.api.recipe.entity.RecipeEntity;
import java.util.List;
import org.springframework.http.HttpStatus;

public class RecipeUtilMapper {

  public static RecipeResponse mapRecipeEntityListToRecipeResponse(List<RecipeEntity> recipe) {
    RecipeResponse recipeResponse = new RecipeResponse();
    recipeResponse.setErrorMessage(null);
    recipeResponse.setStatus(HttpStatus.OK);
    List<Recipe> recipes = recipe.stream().map(RecipeUtilMapper::mapEntityToDto).toList();
    recipeResponse.setRecipes(recipes);
    return recipeResponse;
  }

  public static RecipeResponse mapRecipeEntityToRecipeResponse(RecipeEntity recipe) {
    RecipeResponse recipeResponse = new RecipeResponse();
    recipeResponse.setErrorMessage(null);
    recipeResponse.setStatus(HttpStatus.OK);
    Recipe recipes = RecipeUtilMapper.mapEntityToDto(recipe);
    recipeResponse.setRecipes(List.of(recipes));
    return recipeResponse;
  }

  private static Recipe mapEntityToDto(RecipeEntity recipeEntity) {
    Recipe recipe = new Recipe();
    recipe.setId(recipeEntity.getId());
    recipe.setCuisine(recipeEntity.getCuisine());
    recipe.setImage(recipeEntity.getImage());
    recipe.setName(recipeEntity.getName());
    recipe.setIngredients(recipeEntity.getIngredients());
    recipe.setInstructions(recipeEntity.getInstructions());
    recipe.setRating(recipeEntity.getRating());
    recipe.setServings(recipeEntity.getServings());
    recipe.setCaloriesPerServing(recipeEntity.getCaloriesPerServing());
    recipe.setMealType(recipeEntity.getMealType());
    recipe.setReviewCount(recipeEntity.getReviewCount());
    return recipe;
  }
}
