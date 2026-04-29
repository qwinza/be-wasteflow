package com.wasteflow.controller;

import com.wasteflow.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, String>> getRecommendations(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsForUser(userId));
    }
}
