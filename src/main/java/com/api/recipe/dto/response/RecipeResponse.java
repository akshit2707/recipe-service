package com.api.recipe.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponse {
  private HttpStatus status;
  private String errorMessage;
  private List<Recipe> recipes;
}
