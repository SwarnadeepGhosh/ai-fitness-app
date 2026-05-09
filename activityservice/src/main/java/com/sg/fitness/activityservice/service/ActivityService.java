package com.sg.fitness.activityservice.service;


import com.sg.fitness.activityservice.dto.ActivityRequest;
import com.sg.fitness.activityservice.dto.ActivityResponse;
import com.sg.fitness.activityservice.model.ActivityEntity;
import com.sg.fitness.activityservice.repository.ActivityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;

    public ActivityResponse trackActivity(ActivityRequest request) {

        ActivityEntity activity = new ActivityEntity(request);
        ActivityEntity savedActivity = activityRepo.save(activity);
        return new ActivityResponse(savedActivity);
    }

}
