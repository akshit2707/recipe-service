package com.api.recipe.repository;

import com.api.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDataRepository extends JpaRepository<RecipeEntity, String> {}
