package com.api.recipe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DummyJsonConfig {

  @Value("${data-source.base-url}")
  private String dummyJsonBaseUrl;

  @Bean
  public WebClient dummyJsonClient() {
    return WebClient.builder().baseUrl(dummyJsonBaseUrl).build();
  }
}
