package com.sg.fitness.activityservice.repository;

import com.sg.fitness.activityservice.model.ActivityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends MongoRepository<ActivityEntity, String> {
    List<ActivityEntity> findByUserId(String userId);
}
