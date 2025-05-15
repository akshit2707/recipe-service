package com.api.recipe.dto.request;

import com.api.recipe.entity.RecipeEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseFromDummyJson {

  private List<RecipeEntity> recipes;
}
