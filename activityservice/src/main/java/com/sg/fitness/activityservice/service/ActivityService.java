package com.sg.fitness.activityservice.service;


import com.sg.fitness.activityservice.dto.ActivityRequest;
import com.sg.fitness.activityservice.dto.ActivityResponse;
import com.sg.fitness.activityservice.model.ActivityEntity;
import com.sg.fitness.activityservice.repository.ActivityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivityService {

    @Autowired
    private UserValidationService userValidationService;
    @Autowired
    private ActivityRepo activityRepo;
    private final KafkaTemplate<String, ActivityEntity> kafkaTemplate;

    public ActivityService(KafkaTemplate<String, ActivityEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${kafka-topic}")
    private String topicName;

    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        ActivityEntity activity = new ActivityEntity(request);
        ActivityEntity savedActivity = activityRepo.save(activity);

        try {
            kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
        } catch (Exception e) {
            log.error("Kafka-Exception occurred: ACTIVITY-SERVICE:: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        return new ActivityResponse(savedActivity);
    }

}
