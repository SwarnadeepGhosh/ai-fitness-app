package com.sg.fitness.activityservice.dto;

import com.sg.fitness.activityservice.model.ActivityEntity;
import com.sg.fitness.activityservice.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponse {
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ActivityResponse(ActivityEntity activityEntity) {
        this.id = activityEntity.getId();
        this.userId = activityEntity.getUserId();
        this.type = activityEntity.getType();
        this.duration = activityEntity.getDuration();
        this.caloriesBurned = activityEntity.getCaloriesBurned();
        this.startTime = activityEntity.getStartTime();
        this.additionalMetrics = activityEntity.getAdditionalMetrics();
        this.createdAt = activityEntity.getCreatedAt();
        this.updatedAt = activityEntity.getUpdatedAt();
    }
}