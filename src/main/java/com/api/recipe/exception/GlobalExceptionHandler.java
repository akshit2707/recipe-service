package com.api.recipe.exception;

import com.api.recipe.dto.response.RecipeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RecipeNotFoundException.class)
  public ResponseEntity<RecipeResponse> recipeNotFoundHandler(
      RecipeNotFoundException recipeNotFoundException) {
    RecipeResponse recipeResponse = new RecipeResponse();
    recipeResponse.setStatus(HttpStatus.NOT_FOUND);
    recipeResponse.setErrorMessage(recipeResponse.getErrorMessage());
    recipeResponse.setRecipes(null);
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(GenericException.class)
  public ResponseEntity<RecipeResponse> recipeNotFoundHandler(GenericException genericException) {
    RecipeResponse recipeResponse = new RecipeResponse();
    recipeResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    recipeResponse.setErrorMessage(genericException.getMessage());
    recipeResponse.setRecipes(null);
    return ResponseEntity.internalServerError().body(recipeResponse);
  }

  @ExceptionHandler(DataLoaderException.class)
  public ResponseEntity<String> dataLoaderExceptionHandler(
      DataLoaderException dataLoaderException) {
    return ResponseEntity.internalServerError().body(dataLoaderException.getMessage());
  }
}
