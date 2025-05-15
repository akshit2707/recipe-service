package com.api.recipe.controller;

import com.api.recipe.service.DataLoaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/v1/load")
@RequiredArgsConstructor
public class DataLoader {

  private final DataLoaderService dataLoaderService;

  @GetMapping("/health")
  public ResponseEntity<String> status() {
    return ResponseEntity.ok("Health is up");
  }

  @PostMapping("/recipes")
  public ResponseEntity<String> loadDataFromExternalApi() {
    log.info("Fetching...");
    return ResponseEntity.ok(dataLoaderService.loadData());
  }
}
