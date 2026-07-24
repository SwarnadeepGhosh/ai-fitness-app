package com.sg.fitness.activityservice.controller;

import com.sg.fitness.activityservice.dto.ActivityRequest;
import com.sg.fitness.activityservice.dto.ActivityResponse;
import com.sg.fitness.activityservice.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/save")
    public ResponseEntity<ActivityResponse> saveActivity(
            @RequestBody ActivityRequest request,
            @RequestHeader("X-User-ID") String userId) {
        if (userId != null) {
            request.setUserId(userId);
        }
        log.info("Inside saveActivity method at controller layer, userId={}", userId);
        return ResponseEntity.ok(activityService.saveActivity(request));
    }

    @GetMapping("")
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId) {
        log.info("Inside getUserActivities method at controller layer, userId={}", userId);
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable String activityId) {
        log.info("Inside getActivityById method at controller layer, activityId={}", activityId);
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}
