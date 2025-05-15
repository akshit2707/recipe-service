package com.api.recipe.service;

import com.api.recipe.dto.response.RecipeResponse;
import com.api.recipe.entity.RecipeEntity;
import com.api.recipe.exception.GenericException;
import com.api.recipe.exception.RecipeNotFoundException;
import com.api.recipe.repository.RecipeDataRepository;
import com.api.recipe.util.RecipeUtilMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

  private final RecipeDataRepository recipeRepository;

  @Override
  public RecipeResponse fetchAllRecipes() {
    List<RecipeEntity> recipe = recipeRepository.findAll();
    return RecipeUtilMapper.mapRecipeEntityListToRecipeResponse(recipe);
  }

  @Override
  @Cacheable(value = "recipe", key = "#id")
  public RecipeResponse getRecipeById(String id) {
    try {
      Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);
      if (recipeEntity.isPresent()) {
        return RecipeUtilMapper.mapRecipeEntityToRecipeResponse(recipeEntity.get());
      } else {
        throw new RecipeNotFoundException("No recipe found");
      }
    } catch (Exception e) {
      throw new GenericException("Something went wrong, try again later");
    }
  }
}
