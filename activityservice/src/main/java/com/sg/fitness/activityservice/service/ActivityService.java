package com.sg.fitness.activityservice.service;


import com.sg.fitness.activityservice.dto.ActivityRequest;
import com.sg.fitness.activityservice.dto.ActivityResponse;
import com.sg.fitness.activityservice.model.ActivityEntity;
import com.sg.fitness.activityservice.repository.ActivityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActivityService {

    @Autowired
    UserValidationService userValidationService;
    @Autowired
    private ActivityRepo activityRepo;

    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        ActivityEntity activity = new ActivityEntity(request);
        ActivityEntity savedActivity = activityRepo.save(activity);
        return new ActivityResponse(savedActivity);
    }

}
