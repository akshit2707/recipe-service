package com.api.recipe.controller;

import com.api.recipe.dto.response.RecipeResponse;
import com.api.recipe.service.RecipeService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeController {

  private final RecipeService recipeService;

  @GetMapping("/health")
  public ResponseEntity<String> status() {
    return ResponseEntity.ok("Health is up");
  }

  @GetMapping("")
  public ResponseEntity<RecipeResponse> getAllRecipes() {
    return ResponseEntity.ok(recipeService.fetchAllRecipes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable String id) {
    if (Objects.isNull(id)) {
      RecipeResponse errorResponse = new RecipeResponse();
      errorResponse.setStatus(HttpStatus.BAD_REQUEST);
      errorResponse.setErrorMessage("Id cannot be null or blank");
      errorResponse.setRecipes(null);
      return ResponseEntity.badRequest().body(errorResponse);
    }
    return ResponseEntity.ok(recipeService.getRecipeById(id));
  }
}
