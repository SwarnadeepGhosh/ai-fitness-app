package com.sg.fitness.activityservice.service;


import com.sg.fitness.activityservice.dto.ActivityRequest;
import com.sg.fitness.activityservice.dto.ActivityResponse;
import com.sg.fitness.activityservice.model.ActivityEntity;
import com.sg.fitness.activityservice.repository.ActivityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityService {

    private final KafkaTemplate<String, ActivityEntity> kafkaTemplate;
    @Autowired
    private UserValidationService userValidationService;
    @Autowired
    private ActivityRepo activityRepo;
    @Value("${kafka-topic}")
    private String topicName;

    public ActivityService(KafkaTemplate<String, ActivityEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ActivityResponse saveActivity(ActivityRequest request) {

//        boolean isValidUser = userValidationService.validateUserByKeycloakId(request.getUserId());
//        if (!isValidUser) {
//            throw new RuntimeException("Invalid User: " + request.getUserId());
//        }

        ActivityEntity activity = new ActivityEntity(request);
        ActivityEntity savedActivity = activityRepo.save(activity);

        try {
            kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
        } catch (Exception e) {
            log.error("Kafka-Exception occurred: ACTIVITY-SERVICE:: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        return new ActivityResponse(savedActivity);
    }

    public List<ActivityResponse> getUserActivities(String userId) {
        List<ActivityEntity> activities = activityRepo.findByUserId(userId);
        return activities.stream()
                .map(ActivityResponse::new)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivityById(String activityId) {
        Optional<ActivityEntity> savedActivityOptional = activityRepo.findById(activityId);
        if (savedActivityOptional.isPresent()) {
            return new ActivityResponse(savedActivityOptional.get());
        } else {
            throw new RuntimeException("Activity not found with id: " + activityId);
        }
    }
}
