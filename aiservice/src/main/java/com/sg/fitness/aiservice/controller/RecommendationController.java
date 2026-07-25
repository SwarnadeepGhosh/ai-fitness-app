package com.sg.fitness.aiservice.controller;

import com.sg.fitness.aiservice.model.Recommendation;
import com.sg.fitness.aiservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    /**
     * Retrieves all recommendations for a specific user.
     * <p>
     * This API is intended for internal or future use and is not consumed by the UI.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    /**
     * Retrieves the recommendation for a specific activity.
     * <p>
     * This API is consumed by the UI to display the AI recommendation for an activity.
     */
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}
