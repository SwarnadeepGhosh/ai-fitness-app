package com.sg.fitness.activityservice.model;

import com.sg.fitness.activityservice.dto.ActivityRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "activities")
@Data
@NoArgsConstructor
public class ActivityEntity {
    @Id
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    @Field("metrics")
    private Map<String, Object> additionalMetrics;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public ActivityEntity(ActivityRequest activityRequest) {
        this.userId = activityRequest.getUserId();
        this.type = activityRequest.getType();
        this.duration = activityRequest.getDuration();
        this.caloriesBurned = activityRequest.getCaloriesBurned();
        this.startTime = activityRequest.getStartTime();
        this.additionalMetrics = activityRequest.getAdditionalMetrics();
    }
}