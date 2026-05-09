package com.sg.fitness.activityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@SpringBootApplication
public class ActivityServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ActivityServiceApp.class, args);
    }

}
