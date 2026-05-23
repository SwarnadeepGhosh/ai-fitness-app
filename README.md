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

![mermaid-diagram-2026-05-23T19-04-32](img/mermaid-diagram-2026-05-23T19-04-32.svg)



```mermaid
flowchart LR
    A[POSTMAN / Frontend] --> B[Gateway]

    %% Core services
    B --> C[Keycloak Auth]
    B --> D[User Service]
    B --> E[Activity Service]
    B --> G[AI Recommendation Service]

    %% Kafka as async bridge
    E --> F[KAFKA Messaging]
    F --> G

    %% Databases
    D --> DDB[(User DB: PostgreSQL)]
    E --> EDB[(Activity DB: MongoDB)]
    G --> GDB[(AI DB: MongoDB)]

    %% External integration
    G --> H[Google Gemini LLM]

    %% Infra
    I[Config Server] --> B
    J[Eureka Registry] --> B

    %% Styling for clarity
    style B fill:#f9f,stroke:#333,stroke-width:1px
    style C fill:#9cf,stroke:#333,stroke-width:1px
    style D fill:#c9f,stroke:#333,stroke-width:1px
    style E fill:#c9f,stroke:#333,stroke-width:1px
    style F fill:#ffd,stroke:#333,stroke-width:1px
    style G fill:#c9f,stroke:#333,stroke-width:1px
    style H fill:#9f9,stroke:#333,stroke-width:1px
    style I fill:#ccc,stroke:#333,stroke-width:1px
    style J fill:#ccc,stroke:#333,stroke-width:1px

```



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



---

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



---

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



---

## 🤖 AI Service

- Remote Swagger URL: 
- Local Swagger URL : [http://localhost:8084/docs.html](http://localhost:8084/docs.html)
- 📘 API Details

| #    | API                             | Method | Endpoint                                   | Description | Request | Response |
| ---- | ------------------------------- | ------ | ------------------------------------------ | ----------- | ------- | -------- |
| 1    | Get  Recommendation by user     | GET    | /api/recommendations/user/{userId}         |             |         |          |
| 2    | Get  Recommendation by activity | GET    | /api/recommendations/activity/{activityId} |             |         |          |



### **Google Gemini** Integration

- Visit [Build with Gemini on Google AI Studio](https://aistudio.google.com/prompts/new_chat) -> Click on [Get API Key](https://aistudio.google.com/api-keys?project=gen-lang-client-0249270144)

- **[Documentation - Gemini API](https://ai.google.dev/gemini-api/docs#java)**

- <details>
      <summary>🔽🔼 **Gemini API Key Details (Highly Secret)** </summary>
    ```sh
    
  
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
  
  
  
    ```
      </details>



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

- <details>
      <summary>🔽🔼 ***GeminiService.java*** - making api call </summary>
  
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
  
      </details>
  
- <details>
      <summary>🔽🔼 ***ActivityAIService.java*** - Creating Prompt and calling GeminiService from here.</summary>
  
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
  
      </details>
  
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
  
  # Activity Service API Response: 
  {
      "additionalMetrics": {
          "laps": 24,
          "poolLengthMeters": 25,
          "averageHeartRate": 128,
          "strokeType": "Freestyle"
      },
      "caloriesBurned": 280,
      "createdAt": "2026-05-24T00:22:17.057752",
      "duration": 30,
      "id": "6a11f761c5041d87cdc55323",
      "startTime": "2026-05-10T06:45:00",
      "type": "SWIMMING",
      "updatedAt": "2026-05-24T00:22:17.057752",
      "userId": "dc9d9946-dd7b-4a4c-a123-0a5b4a7f7f09"
  }
  ```
  
- <details>
      <summary>🔽🔼 **Response From Gemini(AI) 3rd Party API :**</summary>
  
  
    ```json
    {
      "candidates": [
        {
          "content": {
            "parts": [
              {
                "text": "{\n  \"analysis\": {\n    \"overall\": \"This 30-minute freestyle session shows a consistent effort covering a total distance of 600 meters. With an average heart rate of 128 bpm, the intensity remained in a steady-state aerobic zone, making it an excellent workout for cardiovascular health and building a foundational fitness base without overtaxing the central nervous system.\",\n    \"pace\": \"The pace averaged approximately 5:00 per 100 meters. This is a relaxed, controlled pace often associated with recovery swims or a focus on stroke mechanics. Given the total laps, there is significant potential to increase speed by incorporating structured intervals.\",\n    \"heartRate\": \"An average heart rate of 128 bpm suggests the user was working at roughly 60-70% of their maximum heart rate (Zone 2). This is the 'fat-burning' zone and is ideal for long-term endurance, though it lacks the anaerobic stimulus required for significant speed gains.\",\n    \"caloriesBurned\": \"Burning 280 calories in 30 minutes is consistent with moderate-intensity swimming. This energy expenditure is effective for weight maintenance and reflects a steady, non-stop effort throughout the duration of the activity.\"\n  },\n  \"improvements\": [\n    {\n      \"area\": \"Stroke Efficiency\",\n      \"recommendation\": \"Focus on your 'SWOLF' score by counting strokes per length. Aim to reduce the number of strokes required to cover 25 meters by emphasizing a stronger pull and a longer glide phase.\"\n    },\n    {\n      \"area\": \"Turn Mechanics\",\n      \"recommendation\": \"If currently doing 'open turns', consider learning or refining flip turns. This maintains momentum and keeps the heart rate more consistent by eliminating the brief pause at the wall.\"\n    },\n    {\n      \"area\": \"Kick Power\",\n      \"recommendation\": \"Incorporate specific kickboard sets to strengthen the lower body. A stronger kick helps maintain a high body position in the water, reducing drag and increasing overall pace.\"\n    }\n  ],\n  \"suggestions\": [\n    {\n      \"workout\": \"Freestyle Interval Training\",\n      \"description\": \"After a 100m warm-up, perform 8 x 50m sprints with 20 seconds of rest between each. Focus on maintaining a higher heart rate (145-155 bpm) during the sprints, followed by a 100m easy cool-down.\"\n    },\n    {\n      \"workout\": \"The Pyramid Set\",\n      \"description\": \"Swim 1 lap (rest 15s), 2 laps (rest 20s), 3 laps (rest 30s), then 2 laps (rest 20s), and 1 lap (rest 15s). This variation in distance helps build both speed and stamina.\"\n    }\n  ],\n  \"safety\": [\n    \"Perform dynamic shoulder stretches (arm circles, wall slides) before entering the water to prevent rotator cuff strain.\",\n    \"Maintain hydration by drinking water before and after the session; despite being in a pool, the body still loses significant fluids through sweat.\",\n    \"Ensure proper lane etiquette and be mindful of other swimmers' speeds to avoid mid-lane collisions.\",\n    \"If you experience any sharp pain in the shoulder or impingement sensations, stop immediately and switch to a kick-only drill.\"\n  ]\n}",
                "thoughtSignature": "EtEbCs4bAQw51sf2DaGmkZbWi5b"
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
  
  
  ​    </details>
  
- <details>
      <summary>🔽🔼 **Text node of Gemini AI Response** (Which is of predefined structure we defined in Prompt)</summary>
  
  
  
    ```json
  {
    "analysis": {
      "overall": "This 30-minute freestyle session shows a consistent effort covering a total distance of 600 meters. With an average heart rate of 128 bpm, the intensity remained in a steady-state aerobic zone, making it an excellent workout for cardiovascular health and building a foundational fitness base without overtaxing the central nervous system.",
      "pace": "The pace averaged approximately 5:00 per 100 meters. This is a relaxed, controlled pace often associated with recovery swims or a focus on stroke mechanics. Given the total laps, there is significant potential to increase speed by incorporating structured intervals.",
      "heartRate": "An average heart rate of 128 bpm suggests the user was working at roughly 60-70% of their maximum heart rate (Zone 2). This is the 'fat-burning' zone and is ideal for long-term endurance, though it lacks the anaerobic stimulus required for significant speed gains.",
      "caloriesBurned": "Burning 280 calories in 30 minutes is consistent with moderate-intensity swimming. This energy expenditure is effective for weight maintenance and reflects a steady, non-stop effort throughout the duration of the activity."
    },
    "improvements": [
      {
        "area": "Stroke Efficiency",
        "recommendation": "Focus on your 'SWOLF' score by counting strokes per length. Aim to reduce the number of strokes required to cover 25 meters by emphasizing a stronger pull and a longer glide phase."
      },
      {
        "area": "Turn Mechanics",
        "recommendation": "If currently doing 'open turns', consider learning or refining flip turns. This maintains momentum and keeps the heart rate more consistent by eliminating the brief pause at the wall."
      },
      {
        "area": "Kick Power",
        "recommendation": "Incorporate specific kickboard sets to strengthen the lower body. A stronger kick helps maintain a high body position in the water, reducing drag and increasing overall pace."
      }
    ],
    "suggestions": [
      {
        "workout": "Freestyle Interval Training",
        "description": "After a 100m warm-up, perform 8 x 50m sprints with 20 seconds of rest between each. Focus on maintaining a higher heart rate (145-155 bpm) during the sprints, followed by a 100m easy cool-down."
      },
      {
        "workout": "The Pyramid Set",
        "description": "Swim 1 lap (rest 15s), 2 laps (rest 20s), 3 laps (rest 30s), then 2 laps (rest 20s), and 1 lap (rest 15s). This variation in distance helps build both speed and stamina."
      }
    ],
    "safety": [
      "Perform dynamic shoulder stretches (arm circles, wall slides) before entering the water to prevent rotator cuff strain.",
      "Maintain hydration by drinking water before and after the session; despite being in a pool, the body still loses significant fluids through sweat.",
      "Ensure proper lane etiquette and be mindful of other swimmers' speeds to avoid mid-lane collisions.",
      "If you experience any sharp pain in the shoulder or impingement sensations, stop immediately and switch to a kick-only drill."
    ]
  }
    ```
  
  
  ​    </details>
  
  

Flow Diagram until this point: 

<img src="img/diagram1.png" alt="diagram1.png" style="zoom: 50%;" />



---

## Config Server
