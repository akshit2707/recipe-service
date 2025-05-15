package com.api.recipe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
public class RecipeEntity {
  @Id private String id;
  private String name;
  private List<String> ingredients;
  private List<String> instructions;
  private Integer prepTimeMinutes;
  private Integer cookTimeMinutes;
  private Integer servings;
  private String difficulty;
  private String cuisine;
  private Integer caloriesPerServing;
  private List<String> tags;
  private Integer userId;
  private String image;
  private Double rating;
  private Integer reviewCount;
  private List<String> mealType;
}
