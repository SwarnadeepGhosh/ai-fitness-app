package com.sg.fitness.aiservice.service;

import com.sg.fitness.aiservice.model.Activity;
import com.sg.fitness.aiservice.repository.RecommendationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final RecommendationRepo recommendationRepository;
    private final ActivityAIService activityAIService;

    @KafkaListener(topics = "${kafka-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void processActivity(Activity activity) {
        log.info("Received activity for processing: {}", activity.getUserId());
        activityAIService.generateRecommendation(activity);
//        log.info("Generated Recommendation: {}", aiService.generateRecommendation(activity));
//        Recommendation recommendation = aiService.generateRecommendation(activity);
//        recommendationRepository.save(recommendation);
    }
}
