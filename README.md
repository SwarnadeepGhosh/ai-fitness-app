# AI-Powered Fitness Application

[Live Frontend Link]()

[Live Backend Swagger Link]()



<p>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="springboot" width="100" height="100"/>
  &nbsp;&nbsp;
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original.svg" alt="react" width="100" height="100"/>
  &nbsp;&nbsp;
  <img src="https://spring.io/img/projects/spring-cloud.svg" alt="eureka" width="100" height="100"/>
  &nbsp;&nbsp;
  <img src="https://icon.icepanel.io/GCP/svg/Cloud-API-Gateway.svg" alt="gateway" width="100" height="100"/>
  &nbsp;&nbsp;
  <img src="https://upload.wikimedia.org/wikipedia/commons/b/b4/Logo_of_Keycloak.svg" alt="keycloak" width="200" height="100"/>
  &nbsp;&nbsp;
  <img src="https://www.svgrepo.com/show/353950/kafka.svg" alt="kafka" width="250" height="100"/>
  &nbsp;&nbsp;
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original.svg" alt="postgresql" width="100" height="100"/>
  &nbsp;&nbsp;
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original.svg" alt="mysql" width="100" height="100"/>
  &nbsp;&nbsp;
  <img src="https://upload.wikimedia.org/wikipedia/commons/9/93/MongoDB_Logo.svg" alt="mongodb" width="200" height="100"/>
  &nbsp;&nbsp;
  <img src="https://upload.wikimedia.org/wikipedia/commons/d/d9/Google_Gemini_logo_2025.svg" alt="gemini" width="150" height="100"/>
  &nbsp;&nbsp;
  <img src="https://uxwing.com/wp-content/themes/uxwing/download/tools-equipment-construction/configuration-icon.svg" alt="configserver" width="100" height="100"/>
</p>




## System

**Tech Stack**

- Spring Boot + React Frontend
- Eureka Server (Spring Cloud Netflix)
- Spring Cloud Gateway
- Keycloak - Authentication and Authorization
- Apache Kafka - Async inter-service communication
- PostgreSQL + MySQL + MongoDB
- Google Gemini API - AI model
- Spring Cloud Config Server



**Key Highlights**

- Fully Featured Fitness app on microservice architecture.
- AI Integration on microservices



### Application Architecture

```mermaid
flowchart LR
    A[POSTMAN OR FRONTEND] --> B[Gateway]
    B --> C[KEYCLOAK<br/>Auth]
    B --> D[USER<br/>Profile]
    B --> E[Activity<br/>Fitness Details]
    B --> F[KAFKA<br/>Messaging]
    B --> G[AI Service<br/>Recommendation]
    
    D --> DDB[(User DB<br/>PostgreSQL)]
    E --> EDB[(Activity DB<br/>MySQL)]
    G --> GDB[(AI Service DB<br/>MongoDB)]
    
    G --> H[Google's Gemini<br/>LLM]
    
    I[CONFIG SERVER<br/>Config Mgmt] --> B
    J[EUREKA<br/>Service Reg.] --> B
    
    subgraph Backend Microservices
        D
        E
        F
        G
    end
    
    subgraph Infra
        I
        J
    end
    
    subgraph External
        H
    end
    
    style B fill:#f9f,stroke:#333,stroke-width:1px
    style C fill:#9cf,stroke:#333,stroke-width:1px
    style D fill:#c9f,stroke:#333,stroke-width:1px
    style E fill:#c9f,stroke:#333,stroke-width:1px
    style F fill:#c9f,stroke:#333,stroke-width:1px
    style G fill:#c9f,stroke:#333,stroke-width:1px
    style H fill:#9f9,stroke:#333,stroke-width:1px
    style I fill:#ccc,stroke:#333,stroke-width:1px
    style J fill:#ccc,stroke:#333,stroke-width:1px
```

![Updated architecture diagram with Kafka as async communication layer between Activity microservice and AI microservice](https://copilot.microsoft.com/th/id/BCO.e718b1c9-32e9-4f6d-a2e1-0630ca6dee86.png)

# Microservices

## 👤 User Service

- Remote Swagger URL: 
- Local Swagger URL : [http://localhost:8081/docs.html](http://localhost:8081/docs.html)
- 📘 API Details

| #    | API              | Method | Endpoint                     | Description            | Request                                                      | Response                                                     |
| ---- | ---------------- | ------ | ---------------------------- | ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | Register User    | POST   | /api/users/register          | Create a new user      | Body (RegisterRequest):`email*`, `password* (min 6)`, `keycloakId`, `firstName`, `lastName` | UserResponse: `{"id":"string","keycloakId":"string","email":"string","password":"string","firstName":"string","lastName":"string","createdAt":"date-time","updatedAt":"date-time"}` |
| 2    | Get User Profile | GET    | /api/users/{userId}          | Get user details       | Path:`userId* (string)`                                      | UserResponse : `{"id":"string","keycloakId":"string","email":"string","password":"string","firstName":"string","lastName":"string","createdAt":"date-time","updatedAt":"date-time"}` |
| 3    | Validate User    | GET    | /api/users/{userId}/validate | Check if user is valid | Path:`userId* (string)`                                      | `true`                                                       |



> #TODO
>
> 1. Context path





## 🏃 Activity Service

- Remote Swagger URL: 

- Local Swagger URL : [http://localhost:8083/docs.html](http://localhost:8083/docs.html)

- MongoDB Atlas URL: [MongoDB Cloud | Clusters](https://cloud.mongodb.com/v2/69fb994bb5a8f4731c06f720#/clusters)

- 📘 API Details : 

- | #    | API             | Method | Endpoint        | Description                 | Request                                                      | Response                                                     |
  | ---- | --------------- | ------ | --------------- | --------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
  | 1    | Save Activities | POST   | /api/activities | Save an activity in MongoDB | `{"userId":"user3098","type":"SWIMMING","duration":30,"caloriesBurned":280,"startTime":"2026-05-10T06:45:00","additionalMetrics":{"laps":24,"poolLengthMeters":25,"averageHeartRate":128,"strokeType":"Freestyle"}}` | `{"additionalMetrics":{"laps":24,"poolLengthMeters":25,"averageHeartRate":128,"strokeType":"Freestyle"},"caloriesBurned":280,"createdAt":"2026-05-09T12:18:54.8861262","duration":30,"id":"69fed8d76a2a50f1c0dfd4fd","startTime":"2026-05-10T06:45:00","type":"SWIMMING","updatedAt":"2026-05-09T12:18:54.8861262","userId":"user3098"}` |
  |      |                 |        |                 |                             |                                                              |                                                              |



- **Remote MongoDB Details**

  ```sh
  mongodb username : swarnadeep
  password: swarna@123
  mongodb cluster name: swarnadeep
  Connection URL : mongodb+srv://swarnadeep:swarna@123@swarnadeep.lx7vex8.mongodb.net/
  ```

- ***application.yaml*** for MongoDB 

  ```yaml
  spring:
    mongodb:
      uri: "mongodb+srv://swarnadeep:swarna%40123@swarnadeep.lx7vex8.mongodb.net/aifitness?retryWrites=true&w=majority"
      database: aifitness
      auto-index-creation: true
  ```





## 📡Eureka Naming Server

- Remote URL: 
- Local URL : [http://localhost:8761/](http://localhost:8761/)


---

## Inter Service Communication

[Spring Framework Rest Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html) :

- The Spring Framework provides the following choices for making calls to REST endpoints:

  - [`RestClient`](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient) — synchronous client with a fluent API

  - [`WebClient`](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-webclient) — non-blocking, reactive client with fluent API

  - [`RestTemplate`](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-resttemplate) — synchronous client with template method API, now deprecated in favor of `RestClient`

  - [HTTP Service Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-service-client) — annotated interface backed by generated proxy

- We will use WebClient here. 



### Spring WebClient

-  [**WebClient**](https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html) is a non-blocking, reactive client to perform HTTP requests. It was introduced in 5.0 and offers an alternative to the `RestTemplate`, with support for synchronous, asynchronous, and streaming scenarios.

- `WebClient` supports the following:

  - Non-blocking I/O

  - Reactive Streams back pressure

  - High concurrency with fewer hardware resources

  - Functional-style, fluent API that takes advantage of lambda expressions

  - Synchronous and asynchronous interactions

  - Streaming up to or streaming down from a server.

- **Spring WebClient is a non-blocking and reactive web client to perform HTTP requests.** **It is also the replacement for the classic [RestTemplate](https://www.geeksforgeeks.org/springboot/spring-resttemplate/)**. It is a part of **spring-webflux library** and also offers support for both synchronous and asynchronous operations. The DefaultWebClient class implements this WebClient interface.

- `WebClient` needs an HTTP client library to perform requests. There is built-in support for the following:

  - [Reactor Netty](https://github.com/reactor/reactor-netty)
  - [JDK HttpClient](https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpClient.html)
  - [Jetty Reactive HttpClient](https://github.com/jetty-project/jetty-reactive-httpclient)
  - [Apache HttpComponents](https://hc.apache.org/index.html)
  - Others can be plugged in via `ClientHttpConnector`.

- **How to Use WebClient in Spring Boot Project?**

  Add this dependency to the pom.xml file.

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
  </dependency>
  ```

- ***WebClientConfig.java*** - Creating WebClientConfig to call external api

  ```java
  import org.springframework.cloud.client.loadbalancer.LoadBalanced;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.web.reactive.function.client.WebClient;
  
  @Configuration
  public class WebClientConfig {
  
      // @LoadBalanced ensure to use service names registered on eureka, not their hostname or IP addresses
      @Bean
      @LoadBalanced
      public WebClient.Builder webClientBuilder() {
          return WebClient.builder();
      }
  
      @Bean
      public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
          return webClientBuilder
                  .baseUrl("http://USERSERVICE")
                  .build();
      }
  }
  ```

- ***UserValidationService.java*** - Calling external API

  ```java
  import lombok.extern.slf4j.Slf4j;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.stereotype.Service;
  import org.springframework.web.reactive.function.client.WebClient;
  import org.springframework.web.reactive.function.client.WebClientResponseException;
  
  @Service
  @Slf4j
  public class UserValidationService {
      @Autowired
      private WebClient userServiceWebClient;
  
      public boolean validateUser(String userId) {
          log.info("Calling User Validation API for userId: {}", userId);
          try {
              return Boolean.TRUE.equals(userServiceWebClient.get()
                      .uri("/api/users/{userId}/validate", userId)
                      .retrieve()
                      .bodyToMono(Boolean.class)
                      .block());
          } catch (WebClientResponseException e) {
              if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                  throw new RuntimeException("User Not Found: " + userId);
              else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                  throw new RuntimeException("Invalid Request: " + userId);
          }
          return false;
      }
  }
  ```

- API Call Example (`cURL`): 
  ```sh
  # Validate api
  curl --location 'http://localhost:8081/api/users/dc9d9946-dd7b-4a4c-a123-0a5b4a7f7f09/validate' \
  --header 'accept: */*'
  # Response : true
  
  
  # Saving Activity with saved user
  curl --location 'http://localhost:8083/api/activities' \
  --header 'accept: */*' \
  --header 'Content-Type: application/json' \
  --data '{
    "userId": "dc9d9946-dd7b-4a4c-a123-0a5b4a7f7f09",
    "type": "SWIMMING",
    "duration": 30,
    "caloriesBurned": 280,
    "startTime": "2026-05-10T06:45:00",
    "additionalMetrics": {
      "laps": 24,
      "poolLengthMeters": 25,
      "averageHeartRate": 128,
      "strokeType": "Freestyle"
    }
  }'
  
  # Response : 
  {
      "additionalMetrics": {
          "laps": 24,
          "poolLengthMeters": 25,
          "averageHeartRate": 128,
          "strokeType": "Freestyle"
      },
      "caloriesBurned": 280,
      "createdAt": "2026-05-11T23:37:08.604859",
      "duration": 30,
      "id": "6a021acc00980700fb23d824",
      "startTime": "2026-05-10T06:45:00",
      "type": "SWIMMING",
      "updatedAt": "2026-05-11T23:37:08.604859",
      "userId": "dc9d9946-dd7b-4a4c-a123-0a5b4a7f7f09"
  }
  ```

### **Kafka** - Async Communication

- Setup : 
  
  - **Kafka Producer => Activity Service**
  - **Kafka Consumer => AI Service** (to generate recommendation)
  
- Common Dependency: ***pom.xml***

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-kafka</artifactId>
  </dependency>
  ```

  

#### Producer Code

- **Producer**: ***application.yml***

  ```properties
  spring:
    kafka:
      # bootstrap-servers: localhost:9092
      bootstrap-servers: pkc-l7pr2.ap-south-1.aws.confluent.cloud:9092
      properties:
        security.protocol: SASL_SSL
        sasl.mechanism: PLAIN
        sasl.jaas.config: org.apache.kafka.common.security.plain.PlainLoginModule required username='CUUOKL3RGKJVNKOV' password='cfltTxZBE4MgNNnav4jo2SDiABuLjtVGLguZuGmulYZPIUdosp0XAIQB9Q9Dl1GA';
        session.timeout.ms: 45000
        client.id: ccloud-springboot-client-1912bc0e-dc67-49f8-812c-786705966c96
      producer:
        key-serializer: org.apache.kafka.common.serialization.StringSerializer
        value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
  
  kafka-topic: ai-fitness
  ```

- Topic Creation : ***KafkaConfig.java***

  ```java
  import org.apache.kafka.clients.admin.NewTopic;
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  
  @Configuration
  public class KafkaConfig {
  
      @Value("${kafka-topic}")
      private String topicName;
  
      @Bean
      public NewTopic createTopic() {
          return new NewTopic(topicName, 1, (short) 3);
      }
  }
  ```

- Producer : **Saving Data in Kafka Topic:** ***ActivityService.java***

  ```java
  ...
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.kafka.core.KafkaTemplate;
  
  public class ActivityService {
  ...
      private final KafkaTemplate<String, ActivityEntity> kafkaTemplate;
  
      public ActivityService(KafkaTemplate<String, ActivityEntity> kafkaTemplate) {
          this.kafkaTemplate = kafkaTemplate;
      }
  
      @Value("${kafka-topic}")
      private String topicName;
  
  ...
          try {
              kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
          } catch (Exception e) {
              log.error("Kafka-Exception occurred: ACTIVITY-SERVICE:: " + e.getClass().getSimpleName() + ": " + e.getMessage());
          }
  ...
  ```

  

#### Consumer Code

- **Consumer**: ***application.yml***

  ```properties
  spring:
    kafka:
      # bootstrap-servers: localhost:9092
      bootstrap-servers: pkc-l7pr2.ap-south-1.aws.confluent.cloud:9092
      properties:
        security.protocol: SASL_SSL
        sasl.mechanism: PLAIN
        sasl.jaas.config: org.apache.kafka.common.security.plain.PlainLoginModule required username='CUUOKL3RGKJVNKOV' password='cfltTxZBE4MgNNnav4jo2SDiABuLjtVGLguZuGmulYZPIUdosp0XAIQB9Q9Dl1GA';
        session.timeout.ms: 45000
        client.id: ccloud-springboot-client-1912bc0e-dc67-49f8-812c-786705966c96
  
      consumer:
        group-id: aifitness-group
        auto-offset-reset: earliest
        key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
        value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
  #      value-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
        properties:
          spring.deserializer.value.delegate.class: org.springframework.kafka.support.serializer.JsonDeserializer
          spring.json.trusted.packages: "*"
          spring.json.use.type.headers: false
          spring.json.value.default.type: com.sg.fitness.aiservice.model.Activity
  
  kafka-topic: ai-fitness
  ```

- Consumer: **Listening and fetch Data from Kafka Topic:** ***ActivityService.java***

  ```java
  import org.springframework.kafka.annotation.KafkaListener;
  ...
  public class ActivityMessageListener {
  
      private final RecommendationRepo recommendationRepository;
  
      @KafkaListener(topics = "${kafka-topic}", groupId = "${spring.kafka.consumer.group-id}")
      public void processActivity(Activity activity) {
          log.info("Received activity for processing: {}", activity.getUserId());
  //        log.info("Generated Recommendation: {}", aiService.generateRecommendation(activity));
  //        Recommendation recommendation = aiService.generateRecommendation(activity);
  //        recommendationRepository.save(recommendation);
      }
  }
  ```





## 🤖 AI Service

- Remote Swagger URL: 
- Local Swagger URL : [http://localhost:8084/docs.html](http://localhost:8084/docs.html)
- 📘 API Details

| #    | API                             | Method | Endpoint                                   | Description | Request | Response |
| ---- | ------------------------------- | ------ | ------------------------------------------ | ----------- | ------- | -------- |
| 1    | Get  Recommendation by user     | GET    | /api/recommendations/user/{userId}         |             |         |          |
| 2    | Get  Recommendation by activity | GET    | /api/recommendations/activity/{activityId} |             |         |          |



### Google Gemini Integration

- Visit [Build with Gemini on Google AI Studio](https://aistudio.google.com/prompts/new_chat) -> Click on [Get API Key](https://aistudio.google.com/api-keys?project=gen-lang-client-0249270144)
- **[Documentation - Gemini API](https://ai.google.dev/gemini-api/docs#java)**
- API Key Details (Highly Secret)

```sh
# Gemini API key details
# API Key: AIzaSyCJFxa0hd7u0Fa1-tA5UBIjNW0a_iIyCmM
# Name: Default Gemini API Key
# Project name: projects/589201942080
# Project number: 589201942080


# Gemini cURL Quickstart
curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent" \
  -H 'Content-Type: application/json' \
  -H 'X-goog-api-key: AIzaSyCJFxa0hd7u0Fa1-tA5UBIjNW0a_iIyCmM' \
  -X POST \
  -d '{
    "contents": [
      {
        "parts": [
          {
            "text": "Explain how AI works in a few words"
          }
        ]
      }
    ]
  }'
  

Response :  {
    "candidates": [
        {
            "content": {
                "parts": [
                    {
                        "text": "AI analyzes massive amounts of **data** to find **patterns** and make **predictions**."
                    }
                ],
                "role": "model"
            },
            "finishReason": "STOP",
            "index": 0
        }
    ],
    "usageMetadata": {
        "promptTokenCount": 8,
        "candidatesTokenCount": 18,
        "totalTokenCount": 266,
        "promptTokensDetails": [
            {
                "modality": "TEXT",
                "tokenCount": 8
            }
        ],
        "thoughtsTokenCount": 240,
        "serviceTier": "standard"
    },
    "modelVersion": "gemini-3-flash-preview",
    "responseId": "SPUHarTgH6bNjuMP0O3T-Qk"
}
```



**Code Snapshot:** 

- ***application.yml***

  ```yml
  gemini:
    api:
      url: https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent
      key: AIzaSyCJFxa0hd7u0Fa1-tA5UBIjNW0a_iIyCmM
  ```

- ***pom.xml*** - as we need webclient to call gemini api

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-webflux</artifactId>
  </dependency>
  ```

- ***GeminiService.java*** - making api call 

  ```java
  public class GeminiService {
      
      @Value("${gemini.api.url}")
      private String geminiApiUrl;
      @Value("${gemini.api.key}")
      private String geminiApiKey;
  
      private final WebClient webClient;
      public GeminiService(WebClient.Builder webClientBuilder) {
          this.webClient = webClientBuilder.build();
      }
  
      // Mapping with gemini api request body and making external api call
      public String getAnswer(String question) {
          Map<String, Object> requestBody = Map.of(
                  "contents", new Object[]{
                          Map.of("parts", new Object[]{
                                  Map.of("text", question)
                          })
                  }
          );
  
          String response = webClient.post()
                  .uri(geminiApiUrl)
                  .header("Content-Type", "application/json")
                  .header("X-goog-api-key", geminiApiKey)
                  .bodyValue(requestBody)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
          return response;
      }
  }
  ```

- ***ActivityAIService.java*** - Creating Prompt and calling GeminiService from here.

  ```java
  public class ActivityAIService {
      private final GeminiService geminiService;
  
      public Recommendation generateRecommendation(Activity activity) {
          String prompt = createPromptForActivity(activity);
          String aiResponse = geminiService.getAnswer(prompt);
          log.info("RESPONSE FROM AI: {} ", aiResponse);
  //        return processAiResponse(activity, aiResponse);
          return null;
      }
  
      private String createPromptForActivity(Activity activity) {
          return String.format("""
                          Analyze this fitness activity and provide detailed recommendations in the following EXACT JSON format:
                          {
                            "analysis": {
                              "overall": "Overall analysis here",
                              "pace": "Pace analysis here",
                              "heartRate": "Heart rate analysis here",
                              "caloriesBurned": "Calories analysis here"
                            },
                            "improvements": [
                              {
                                "area": "Area name",
                                "recommendation": "Detailed recommendation"
                              }
                            ],
                            "suggestions": [
                              {
                                "workout": "Workout name",
                                "description": "Detailed workout description"
                              }
                            ],
                            "safety": [
                              "Safety point 1",
                              "Safety point 2"
                            ]
                          }
  
                          Analyze this activity:
                          Activity Type: %s
                          Duration: %d minutes
                          Calories Burned: %d
                          Additional Metrics: %s
                                  
                          Provide detailed analysis focusing on performance, improvements, next workout suggestions, and safety guidelines.
                          Ensure the response follows the EXACT JSON format shown above.
                          """,
                  activity.getType(),
                  activity.getDuration(),
                  activity.getCaloriesBurned(),
                  activity.getAdditionalMetrics()
          );
      }
  }
  ```

- ***WebClientConfig.java***

  ```java
  @Configuration
  public class WebClientConfig {
      @Bean
  //    @LoadBalanced (@LoadBalanced ensure to use service names registered on eureka, not their hostname or IP addresses)
  //    We cant use @Loadbalanced as gemini api url is not registered in our eureka server.  
      public WebClient.Builder webClientBuilder() {
          return WebClient.builder();
      }
  }
  ```



#### Testing

- Curl
  ```sh
  curl --location 'http://localhost:8083/api/activities' \
  --header 'accept: */*' \
  --header 'Content-Type: application/json' \
  --data '{
    "userId": "dc9d9946-dd7b-4a4c-a123-0a5b4a7f7f09",
    "type": "SWIMMING",
    "duration": 30,
    "caloriesBurned": 280,
    "startTime": "2026-05-10T06:45:00",
    "additionalMetrics": {
      "laps": 24,
      "poolLengthMeters": 25,
      "averageHeartRate": 128,
      "strokeType": "Freestyle"
    }
  }'
  ```

- <details>
      <summary>🔽🔼 **Response From AI :**</summary>

    ```json
    {
      "candidates": [
        {
          "content": {
            "parts": [
              {
                "text": "{\n  \"analysis\": {\n    \"overall\": \"This 30-minute freestyle session shows a consistent effort covering a total distance of 600 meters. With an average heart rate of 128 bpm, the intensity remained in a steady-state aerobic zone, making it an excellent workout for cardiovascular health and building a foundational fitness base without overtaxing the central nervous system.\",\n    \"pace\": \"The pace averaged approximately 5:00 per 100 meters. This is a relaxed, controlled pace often associated with recovery swims or a focus on stroke mechanics. Given the total laps, there is significant potential to increase speed by incorporating structured intervals.\",\n    \"heartRate\": \"An average heart rate of 128 bpm suggests the user was working at roughly 60-70% of their maximum heart rate (Zone 2). This is the 'fat-burning' zone and is ideal for long-term endurance, though it lacks the anaerobic stimulus required for significant speed gains.\",\n    \"caloriesBurned\": \"Burning 280 calories in 30 minutes is consistent with moderate-intensity swimming. This energy expenditure is effective for weight maintenance and reflects a steady, non-stop effort throughout the duration of the activity.\"\n  },\n  \"improvements\": [\n    {\n      \"area\": \"Stroke Efficiency\",\n      \"recommendation\": \"Focus on your 'SWOLF' score by counting strokes per length. Aim to reduce the number of strokes required to cover 25 meters by emphasizing a stronger pull and a longer glide phase.\"\n    },\n    {\n      \"area\": \"Turn Mechanics\",\n      \"recommendation\": \"If currently doing 'open turns', consider learning or refining flip turns. This maintains momentum and keeps the heart rate more consistent by eliminating the brief pause at the wall.\"\n    },\n    {\n      \"area\": \"Kick Power\",\n      \"recommendation\": \"Incorporate specific kickboard sets to strengthen the lower body. A stronger kick helps maintain a high body position in the water, reducing drag and increasing overall pace.\"\n    }\n  ],\n  \"suggestions\": [\n    {\n      \"workout\": \"Freestyle Interval Training\",\n      \"description\": \"After a 100m warm-up, perform 8 x 50m sprints with 20 seconds of rest between each. Focus on maintaining a higher heart rate (145-155 bpm) during the sprints, followed by a 100m easy cool-down.\"\n    },\n    {\n      \"workout\": \"The Pyramid Set\",\n      \"description\": \"Swim 1 lap (rest 15s), 2 laps (rest 20s), 3 laps (rest 30s), then 2 laps (rest 20s), and 1 lap (rest 15s). This variation in distance helps build both speed and stamina.\"\n    }\n  ],\n  \"safety\": [\n    \"Perform dynamic shoulder stretches (arm circles, wall slides) before entering the water to prevent rotator cuff strain.\",\n    \"Maintain hydration by drinking water before and after the session; despite being in a pool, the body still loses significant fluids through sweat.\",\n    \"Ensure proper lane etiquette and be mindful of other swimmers' speeds to avoid mid-lane collisions.\",\n    \"If you experience any sharp pain in the shoulder or impingement sensations, stop immediately and switch to a kick-only drill.\"\n  ]\n}",
                "thoughtSignature": "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"
              }
            ],
            "role": "model"
          },
          "finishReason": "STOP",
          "index": 0
        }
      ],
      "usageMetadata": {
        "promptTokenCount": 258,
        "candidatesTokenCount": 740,
        "totalTokenCount": 1956,
        "promptTokensDetails": [
          {
            "modality": "TEXT",
            "tokenCount": 258
          }
        ],
        "thoughtsTokenCount": 958,
        "serviceTier": "standard"
      },
      "modelVersion": "gemini-3-flash-preview",
      "responseId": "AhwIarqmLs3Qg8UP3ffwwQk"
    }
    ```

      
      
      </details>

  

  
