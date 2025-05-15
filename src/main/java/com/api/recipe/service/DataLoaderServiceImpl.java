package com.api.recipe.service;

import com.api.recipe.dto.request.RecipeResponseFromDummyJson;
import com.api.recipe.entity.RecipeEntity;
import com.api.recipe.exception.DataLoaderException;
import com.api.recipe.repository.RecipeDataRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataLoaderServiceImpl implements DataLoaderService {

  private final WebClient dummyJsonClient;

  private final RecipeDataRepository recipeDataRepository;

  @Override
  @CircuitBreaker(name = "recipe", fallbackMethod = "defaultFallback")
  public String loadData() {
    try {
      RecipeResponseFromDummyJson responseFromWebsite =
          dummyJsonClient.get().retrieve().bodyToMono(RecipeResponseFromDummyJson.class).block();
      List<RecipeEntity> recipeFromWebsite = responseFromWebsite.getRecipes();
      int finalCount = 0;
      AtomicInteger count = new AtomicInteger();
      recipeFromWebsite.forEach(
          recipe -> {
            String id = recipe.getId();
            if (recipeDataRepository.findById(id).isEmpty()) {
              recipeDataRepository.save(recipe);
              log.info("Recipe saved with id {}", id);
              count.getAndIncrement();
            }
          });
      finalCount = count.get();
      return "Saved new data: " + finalCount;
    } catch (WebClientException exception) {
      log.error("Unable to save the data");
      throw new DataLoaderException(exception.getMessage());
    }
  }

  public String defaultFallback(Throwable t) {
    log.info("Loading dummy data for testing");
    EasyRandom easyRandom = new EasyRandom();
    RecipeEntity entity = easyRandom.nextObject(RecipeEntity.class);
    entity.setImage("Dummy");
    entity.setCuisine("dummy cuisine");
    entity.setDifficulty("Easy");
    entity.setName("Dummy Recipe");
    entity.setCaloriesPerServing(0);
    entity.setCookTimeMinutes(0);
    entity.setIngredients(List.of("dummy"));
    recipeDataRepository.save(entity);
    log.info(t.getMessage());
    return "Fallback response";
  }
}
