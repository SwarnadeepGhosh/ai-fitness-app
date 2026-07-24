package com.sg.fitness.config;

// import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApp {

    public static void main(String[] args) {

        // Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        // dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(ConfigServerApp.class, args);
    }

}
