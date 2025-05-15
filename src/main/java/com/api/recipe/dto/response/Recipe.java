package com.api.recipe.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
  private String id;
  private String name;
  private List<String> ingredients;
  private List<String> instructions;
  private Integer servings;
  private String cuisine;
  private Integer caloriesPerServing;
  private String image;
  private Double rating;
  private Integer reviewCount;
  private List<String> mealType;
}
