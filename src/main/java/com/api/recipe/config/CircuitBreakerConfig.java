package com.api.recipe.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {
  @Bean
  CircuitBreaker recipeCircuitBreaker(CircuitBreakerRegistry registry) {
    return registry.circuitBreaker("recipe");
  }

  @Bean
  Retry recipeRetry(RetryRegistry registry) {
    return registry.retry("recipe");
  }

  @Bean
  TimeLimiter recipeTimeLimiter(TimeLimiterRegistry registry) {
    return registry.timeLimiter("recipe");
  }
}
