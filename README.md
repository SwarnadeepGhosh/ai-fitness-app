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
      <summary>🔽🔼 **Gemini API Key Details (Highly Secret) </summary>
  
  
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

- Fetch Config URLS: 
  
  | Microservice     | Config Fetch URL (LOCAL)                                     | Config Fetch URL (REMOTE) |
  | ---------------- | ------------------------------------------------------------ | ------------------------- |
  | User Service     | [http://localhost:8888/userservice/default](http://localhost:8888/userservice/default) |                           |
  | Activity Service | [http://localhost:8888/activityservice/default](http://localhost:8888/activityservice/default) |                           |
  | AI Service       | [http://localhost:8888/aiservice/default](http://localhost:8888/aiservice/default) |                           |
  | API Gateway      | [http://localhost:8888/apigateway/default](http://localhost:8888/apigateway/default) |                           |



<u>**Setup Config Server:**</u> 

- **pom.xml** - It should have config server dependency.

- ***application.yaml*** - Here I have mentioned `spring.profiles.active=native` means config should be fetched from local directory. As I created `config/` folder within`src/main/resources/` and kept other application yml files there.

  ```yaml
  server:
    port: 8888
  
  spring:
    application:
      name: configserver
    profiles:
      active: native
    cloud:
      config:
        server:
          native:
            search-locations: classpath:/config
  ```

- On Main class, `@EnableConfigServer` annotation needs to be added.



<u>**Setup Config Client:**</u> 

- Common point : **pom.xml** - It should have config client (`spring-cloud-starter-config`) dependency.

- **User Service:** 

  - ***application.yaml*** - This is spring boot service local yaml files. All configuration removed from here except application name. 

    ```yaml
    spring:
      application:
        name: userservice
      config:
        import: optional:configserver:http://localhost:8888
    ```

  - ***configserver/src/main/resources/config/userservice.yml*** - Here I have mentioned `spring.profiles.active=native` means config should be fetched from local directory. As I created `config/` folder within`src/main/resources/` and kept other application yml files there.

    ```yaml
    server:
      port: 8081
    
    spring:
      datasource:
        url: jdbc:postgresql://free-tier12.aws-ap-south-1.cockroachlabs.cloud:26257/swarna-db-200.testdb
        username: swarnadeep
        password: uLYrds69nT_WNO5vEQn9rQ
    
      jpa:
        show-sql: true
        hibernate:
          ddl-auto: update
        database-platform: org.hibernate.dialect.PostgreSQLDialect
    #    properties:
    #      hibernate:
    #        dialect: org.hibernate.dialect.PostgreSQLDialect
    
    # Naming Server
    eureka:
      instance:
        prefer-ip-address: true
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka
    
    ############### Swagger - openapi ###############
    springdoc:
      api-docs:
        path: /api-docs
      swagger-ui:
        path: /docs.html
    #    operationsSorter: method
    # Swagger path: http://localhost:8080/docs.html or http://localhost:8080/swagger-ui/index.html
    ```

- **Activity Service:** 

  - ***application.yaml*** - This is spring boot service local yaml files. All configuration removed from here except application name. 

    ```yaml
    spring:
      application:
        name: activityservice
      config:
        import: optional:configserver:http://localhost:8888
    ```

  - ***configserver/src/main/resources/config/activityservice.yml*** - Here I have mentioned `spring.profiles.active=native` means config should be fetched from local directory. As I created `config/` folder within`src/main/resources/` and kept other application yml files there.

    ```yaml
    server:
      port: 8083
    
    spring:
      mongodb:
        uri: "mongodb+srv://swarnadeep:swarna%40123@swarnadeep.lx7vex8.mongodb.net/aifitness?retryWrites=true&w=majority"
        database: aifitness
        auto-index-creation: true
    
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
    #    consumer:
    #      group-id: aifitness-group
    #      auto-offset-reset: earliest
    #      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    #      value-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
    #      properties:
    #        spring.deserializer.value.delegate.class: org.springframework.kafka.support.serializer.JsonDeserializer
    #        spring.json.trusted.packages: com.sg.fitness
    
    kafka-topic: ai-fitness
    
    # Naming Server
    eureka:
      instance:
        prefer-ip-address: true
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka
    
    # Swagger
    springdoc:
      api-docs:
        path: /api-docs
      swagger-ui:
        path: /docs.html
    #    operationsSorter: method
    # Swagger path: http://localhost:8080/docs.html or http://localhost:8080/swagger-ui/index.html
    ```

- **AI Service:** 

  - ***application.yaml*** - This is spring boot service local yaml files. All configuration removed from here except application name. 

    ```yaml
    spring:
      application:
        name: aiservice
      config:
        import: optional:configserver:http://localhost:8888
    ```

  - ***configserver/src/main/resources/config/aiservice.yml*** - Here I have mentioned `spring.profiles.active=native` means config should be fetched from local directory. As I created `config/` folder within`src/main/resources/` and kept other application yml files there.

    ```yaml
    server:
      port: 8084
    
    spring:
      mongodb:
        uri: "mongodb+srv://swarnadeep:swarna%40123@swarnadeep.lx7vex8.mongodb.net/aifitnessrecommendation?retryWrites=true&w=majority"
        database: aifitnessrecommendation
        auto-index-creation: true
    
      kafka:
        # bootstrap-servers: localhost:9092
        bootstrap-servers: pkc-l7pr2.ap-south-1.aws.confluent.cloud:9092
        properties:
          security.protocol: SASL_SSL
          sasl.mechanism: PLAIN
          sasl.jaas.config: org.apache.kafka.common.security.plain.PlainLoginModule required username='CUUOKL3RGKJVNKOV' password='cfltTxZBE4MgNNnav4jo2SDiABuLjtVGLguZuGmulYZPIUdosp0XAIQB9Q9Dl1GA';
          session.timeout.ms: 45000
          client.id: ccloud-springboot-client-1912bc0e-dc67-49f8-812c-786705966c96
        #    producer:
        #      key-serializer: org.apache.kafka.common.serialization.StringSerializer
        #      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
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
    gemini:
      api:
        url: https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent
        key: AIzaSyCJFxa0hd7u0Fa1-tA5UBIjNW0a_iIyCmM
    
    # Naming Server
    eureka:
      instance:
        prefer-ip-address: true
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka
    
    # Swagger
    springdoc:
      api-docs:
        path: /api-docs
      swagger-ui:
        path: /docs.html
    #    operationsSorter: method
    # Swagger path: http://localhost:8080/docs.html or http://localhost:8080/swagger-ui/index.html
    ```





---

## API Gateway

| Microservice           | Config Fetch URL (LOCAL)                                     | Config Fetch URL (REMOTE) |
| ---------------------- | ------------------------------------------------------------ | ------------------------- |
| Gateway Service Routes | [http://localhost:8080/actuator/gateway/routes](http://localhost:8080/actuator/gateway/routes) |                           |
| User Service           | [http://localhost:8080/api/users/fa4c9bc0-ccaa-475f-beba-f12c45ab577f](http://localhost:8080/api/users/fa4c9bc0-ccaa-475f-beba-f12c45ab577f) |                           |
| Activity Service       | curl --location 'http://localhost:8080/api/activities' \<br/>--header 'accept: */*' \<br/>--header 'Content-Type: application/json' \<br/>--data '{<br/>  "userId": "fa4c9bc0-ccaa-475f-beba-f12c45ab577f",<br/>  "type": "SWIMMING",<br/>  "duration": 30,<br/>  "caloriesBurned": 280,<br/>  "startTime": "2026-05-10T06:45:00",<br/>  "additionalMetrics": {<br/>    "laps": 24,<br/>    "poolLengthMeters": 25,<br/>    "averageHeartRate": 128,<br/>    "strokeType": "Freestyle"<br/>  }<br/>}' |                           |
| AI Service             | [http://localhost:8080/api/recommendations/activity/6a17cb70cdf583bcf7e5de76](http://localhost:8080/api/recommendations/activity/6a17cb70cdf583bcf7e5de76) |                           |
| API Gateway            | [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health) |                           |



- **Configuration**: 

  - ***pom.xml*** - `spring-cloud-starter-gateway-server-webflux` dependency needs to be added. Also `spring-cloud-starter-netflix-eureka-client` and `spring-cloud-starter-config` are required

  - ***application.yaml*** - This is spring boot service local yaml files. All configuration removed from here except application name. 

    ```yaml
    spring:
      application:
        name: gateway
      config:
        import: optional:configserver:http://localhost:8888
    ```

  - ***configserver/src/main/resources/config/gateway.yml*** - Here I have mentioned `spring.profiles.active=native` means config should be fetched from local directory. As I created `config/` folder within`src/main/resources/` and kept other application yml files there.

    ```yaml
    server:
      port: 8080
    
    spring:
      cloud:
        gateway:
          server:
            webflux:
              routes:
                - id: userservice
                  uri: lb://USERSERVICE
                  predicates:
                    - Path=/api/users/**
    
                - id: activityservice
                  uri: lb://ACTIVITYSERVICE
                  predicates:
                    - Path=/api/activities/**
    
                - id: aiservice
                  uri: lb://AISERVICE
                  predicates:
                    - Path=/api/recommendations/**
    
    # Naming Server
    eureka:
      instance:
    #    prefer-ip-address: true
      client:
        serviceUrl:
          defaultZone: http://localhost:8761/eureka
    
    # Actuator
    management:
      endpoints:
        web:
          exposure:
            include: "*"
      endpoint:
        gateway:
          enabled: true
    ```

  - ***LoggingFilter.java*** - Added to log the hits received.

    ```java
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.cloud.gateway.filter.GatewayFilterChain;
    import org.springframework.cloud.gateway.filter.GlobalFilter;
    import org.springframework.stereotype.Component;
    import org.springframework.web.server.ServerWebExchange;
    import reactor.core.publisher.Mono;
    
    @Component
    public class LoggingFilter implements GlobalFilter {
    
        private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            logger.info("Path of the request received -> {}", exchange.getRequest().getPath());
            return chain.filter(exchange);
        }
    
    }
    
    /* LOGS output: 
    Path of the request received -> /api/recommendations/activity/6a17cb70cdf583bcf7e5de76
    Path of the request received -> /api/users/fa4c9bc0-ccaa-475f-beba-f12c45ab577f
    ```






---

##  🔐 **Oauth2 Integration**

- **Problems without Oauth2:**

  - You had to **share** your credentials all the time. 

  - Security Risk for credential leaking, Limited control.
- **What is Oauth?**
- OAuth (Open Authorization) is a security standard that lets applications access your data on other websites (e.g., Google, Facebook) without giving them your password. Instead of sharing credentials, the app gets a temporary, secure digital "key" (an access token) to perform specific actions on your behalf.
- **Key Terms**

  - **Resource Owner (User)**: Person who owns the account
  - **Third Party application**: Application that wants to access your account.
  - **Resource Server** : Server that holds data that the application wants to access
  - **Authorization Server:** This server handles the authentication and authorization.
  - **Client**: This is the application that requests access to the resource server on behalf of user.
- **<u>Oauth Flow Diagram (Example):</u>**
- <img src="img/oauth.png" alt="oauth flow diagram" style="zoom: 50%;" />
- **authorization-code-flow** Documentation : [Authorization Code Flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/authorization-code-flow)
  <img src="https://mintlify.s3.us-west-1.amazonaws.com/auth0/docs/images/cdy7uua7fh8z/7mWk9No612EefC8uBidCqr/821eb60b0aa953b0d8e4afe897228844/Auth-code-flow-diagram.png" alt="Auth - Auth code flow- Authorization sequence diagram" style="zoom:67%;" />
- (For **Frontend** applications)  [Authorization Code Flow with Proof Key for Code Exchange (PKCE)](https://auth0.com/docs/get-started/authentication-and-authorization-flow/authorization-code-flow-with-pkce)
  <img src="https://mintlify.s3.us-west-1.amazonaws.com/auth0/docs/images/cdy7uua7fh8z/3pstjSYx3YNSiJQnwKZvm5/33c941faf2e0c434a9ab1f0f3a06e13a/auth-sequence-auth-code-pkce.png" alt="Flows - Authorization Code with PKCE - Authorization sequence diagram" style="zoom: 67%;" />
- (For **Machine-to-Machine / Backend-Backend Communication**)  [Client Credentials Flow](https://auth0.com/docs/get-started/authentication-and-authorization-flow/client-credentials-flow)



### 🔒**Keycloak**

<img src="https://www.keycloak.org/resources/images/logo.svg" alt="Keycloak" style="zoom: 33%;" />

- [Keycloak](https://www.keycloak.org/) = Open Source Identity and Access Management
- Add authentication to applications and secure services with minimum effort.
- No need to deal with storing users or authenticating users.



**Installation**

- Guide to Setup Keycloak using OpenJDK, download from here : [https://www.keycloak.org/getting-started/getting-started-zip](https://www.keycloak.org/getting-started/getting-started-zip)

- Start Keycloak Command
  ```sh
  # On Linux, run:
  bin/kc.sh start-dev
  
  # On Windows, run:
  D:/Softwares/keycloak-26.6.4/bin/kc.bat start-dev
  ```

- To change port, add below line in ***conf/keycloak.conf***
  ```properties
  http-port=8181
  ```

- URL to access : [http://localhost:8181/](http://localhost:8181/)

  - Created admin username = `swarnadeep`
  - admin password = `admin`




**<u>Setup Keycloak</u>**

- **Create Realm**

  - A realm in Keycloak is equivalent to a tenant. Each realm allows an administrator to create isolated groups of applications and users. Initially, Keycloak includes a single realm, called `master`. Use this realm only for managing Keycloak and not for managing any applications.

  - Created and enabled realm = `fitness-app`
- **Create Client**

  - Created client with below details, use default for other fields
    - *Client type*: `OpenID Connect`
    - *Client ID*: `oauth2-pkce-client` 
    - *Check* `Standard flow` & `Direct access grants`
    - *Require PKCE* : `On`
    - *PKCE Method* : `S256`
    - Enter Frontend url as `http://localhost:5173` in both *Valid redirect URIs* and *Web origins*
    - Click **Save**
- **Get all Endpoints**

  - Manage Realms -> Realms Settings -> Scroll down, under endpoints, you might get all important endpoints for oauth: 
    - [OpenID Endpoint Configuration ](http://localhost:8181/realms/fitness-app/.well-known/openid-configuration)
    - [SAML 2.0 Identity Provider Metadata ](http://localhost:8181/realms/fitness-app/protocol/saml/descriptor)
- Create a User for *realm* `fitness-app`
  1. Username: `user1`
  2. Email: user1@gmail.com
  3. First name: user1 First
  4. Last name: user1 Last

- Click **Create** -> Credentials -> Set Password
  1. Password: `user1`
  2. Temporary: Off




**<u>Generate AUTH Token using Bruno/Postman</u>**

1. **Grant Type:** `Authorization Code`
2. **Callback URL:** `http://localhost:5173`
3. **Use system browser for OAuth:** `Disabled`
4. **Authorization URL:** `http://localhost:8181/realms/fitness-app/protocol/openid-connect/auth` , Keycloak URL fetch endpoint : [OpenID Endpoint Configuration ](http://localhost:8181/realms/fitness-app/.well-known/openid-configuration)
5. **Access Token URL:** `http://localhost:8181/realms/fitness-app/protocol/openid-connect/token`, Keycloak URL fetch endpoint : [OpenID Endpoint Configuration ](http://localhost:8181/realms/fitness-app/.well-known/openid-configuration)
6. **Client ID:** `oauth2-pkce-client`
7. **Client Secret:** *(Leave blank)*
8. **Scope:** `openid profile roles email`
9. **State:** *(Leave blank)*
10. **Add Credentials to:** `Basic Auth Header`
11. **Use PKCE:** Enabled
12. **Token Source:** `oauth2-fitness-app`
13. **Token ID:** `oauth2-fitness-app`
14. **Add Token To:** Header
15. **Header Prefix:** `Bearer ` 
16. **Refresh Token URL:** *(Leave blank)*
17. **Automatically fetch token if not found:** `Disabled`
18. **Auto refresh token (with refresh URL):** `Disabled`
19. **Additional Parameters:** None

Click **Get Access Token** -> Login with username=`user1` and password=`user1`

Now token is received and saved until expiry time.

<img src="img/oauth-generate-token.png" alt="oauth-generate-token" style="zoom:67%;" />







### Integrating KeyCloak in API Gateway

#### **Level 1 - Basic Testing**

We will include security in Gateway only.

- Add ***pom.xml*** dependency
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
  </dependency>
  ```

- Create ***SecurityConfig.java*** in API gateway, where we are going to add authorization:
  ```java
  @Configuration
  @EnableWebFluxSecurity
  public class SecurityConfig {
  
      @Bean
      public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
          return http
                  .csrf(ServerHttpSecurity.CsrfSpec::disable)
                  .authorizeExchange(exchange -> exchange
                                  .anyExchange().authenticated()
  //                        .pathMatchers("/actuator/*").permitAll()
                  )
                  .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                  .build();
      }
  
  }
  ```

- Add authorization server details in ***gateway.yml*** in config-server
  ```yaml
  spring:
    security:
      oauth2:
        resourceserver:
          jwt:
            jwk-set-uri: http://localhost:8181/realms/fitness-app/protocol/openid-connect/certs
            # Path to get all the URLS -> http://localhost:8181/realms/fitness-app/.well-known/openid-configuration
  ```

- Now restart config-server and api-gateway and hit the GET api, it will throw error: [http://localhost:8080/api/users/fa4c9bc0-ccaa-475f-beba-f12c45ab577f](http://localhost:8080/api/users/fa4c9bc0-ccaa-475f-beba-f12c45ab577f)

- Authenticated CURL: 
  ```sh
  curl --request GET \
    --url http://localhost:8080/api/users/fa4c9bc0-ccaa-475f-beba-f12c45ab577f \
    --header 'accept: */*' \
    --header 'authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ1U1lTamhHV3Z4Ym14N0h4NjhRYUNlR0ZVSDV2dlBNZE5ERWN2VElYSmdvIn0.eyJleHAiOjE3ODM3Mzk4OTksImlhdCI6MTc4MzczOTU5OSwiYXV0aF90aW1lIjoxNzgzNzM3OTkwLCJqdGkiOiJvbnJ0YWM6ZjQxMTE4YzMtMzk3MC02ZTY4LWMwOTktYzU3NThjZjlmYWRlIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgxL3JlYWxtcy9maXRuZXNzLWFwcCIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIyYmQ0OTFhYS0zNjRhLTQ4MzMtOGYzZC0wNzA5OTczZmRlNjciLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJvYXV0aDItcGtjZS1jbGllbnQiLCJzaWQiOiJxeE0xSVJuVDRHQzZxcmJkM0ZFRjd3T24iLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6NTE3MyJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtZml0bmVzcy1hcHAiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoidXNlcjEgRmlyc3QgdXNlcjEgTGFzdCIsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIxIiwiZ2l2ZW5fbmFtZSI6InVzZXIxIEZpcnN0IiwiZmFtaWx5X25hbWUiOiJ1c2VyMSBMYXN0IiwiZW1haWwiOiJ1c2VyMUBnbWFpbC5jb20ifQ.cPOs7087eflMML5neEL5dQqjH0wdO5tRLcNo_1jeupHCWBEdzlwslGyK8Lirg0KK9_vytbtaAomacAk2cKsbx9KeqiNehwbTlSCmb18v-E41ZdJI2L3Ad156BUoxK7EZ4c62OuwLsVUnaVcFmPozgz2upjQUf_JeSIdcmDIiGn0aASnHSJIh1qd9vxSmfqDJ91fu_6ZSS4loPMtirgTwPtaNKSi6cexDKpfy2NGC6MgHYg6Lx9C6Rg3H8p8ZZnfubYyHEP0pL1Rix8SoOQBJj2IUX4eskGI_LxMZCd7XIS0Hj11BGOIlQdG4X_0tkOJNSr4SQ19v_UBTMsxVcXzVoQ'
  ```



#### **Level 2 - User Details Sync from Keycloak to Postgres**

- We need to copy below files in gateway.

  1. **WebClientConfig.java** from Activity Service -- As we need to call User Service from gateway for user validation and syncing.
  2. **RegisterRequest** DTO from User Service
  3. **UserResponse** DTO from User Service

- To achieve User Details Sync from Keycloak to Postgres, we need 2 files - 

- <details>
        <summary>🔽🔼 **KeycloakUserSyncFilter.java** - A filter that intercept every requests and synchronizes incoming user identity information with the downstream user service before the request continues through the gateway.</summary>

    ```java
    import com.nimbusds.jwt.JWTClaimsSet;
    import com.nimbusds.jwt.SignedJWT;
    import com.sg.fitness.gateway.dto.RegisterRequest;
    import com.sg.fitness.gateway.service.UserService;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.server.reactive.ServerHttpRequest;
    import org.springframework.stereotype.Component;
    import org.springframework.web.server.ServerWebExchange;
    import org.springframework.web.server.WebFilter;
    import org.springframework.web.server.WebFilterChain;
    import reactor.core.publisher.Mono;
    
    /**
     * A global web filter that synchronizes incoming user identity information with
     * the downstream user service before the request continues through the gateway.
     *
     * <p>This filter reads the request headers and Keycloak JWT claims, checks
     * whether the user already exists in the user service, and registers the user
     * when needed. In simple terms, it makes sure the gateway and the user service
     * stay in sync before forwarding the request to the rest of the system.</p>
     */
    @Component
    public class KeycloakUserSyncFilter implements WebFilter {
    
        private final Logger logger = LoggerFactory.getLogger(KeycloakUserSyncFilter.class);
        @Autowired
        UserService userService;
    
    //    Taken by implementing org.springframework.cloud.gateway.filter.GlobalFilter
    //    @Override
    //    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    //        logger.info("Path of the request received -> {}", exchange.getRequest().getPath());
    //        return chain.filter(exchange);
    //    }
    
        /**
         * Runs for every incoming request that reaches the gateway and ensures the
         * user represented by the JWT and request headers is synchronized with the
         * downstream user service.
         *
         * <p>In simple terms, this filter first checks whether the request already
         * contains a user identifier. If not, it tries to derive the user identity
         * from the Keycloak JWT claims. Then it asks the user service if the user
         * already exists. If the user is missing, the filter registers the user and
         * only after that continues the original request with the user ID attached to
         * the request headers so downstream services can identify the caller.</p>
         *
         * @param exchange the current server web exchange containing request and response
         * @param chain    the filter chain that continues the request flow
         * @return a reactive completion signal after the sync logic and the original
         * request are handled
         */
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
            String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            RegisterRequest registerRequest = getUserDetails(token);
    
            if (userId == null) {
                userId = registerRequest.getKeycloakId();
            }
    
            if (userId != null && token != null) {
                String finalUserId = userId;
                return userService.validateUser(userId)
                        .flatMap(exist -> {
                            if (!exist) {
                                // The user is not present in the downstream system, so create it first.
                                if (registerRequest != null) {
                                    return userService.registerUser(registerRequest)
                                            .then(Mono.empty());
                                } else {
                                    return Mono.empty();
                                }
                            } else {
                                logger.info("User already exist, Skipping sync.");
                                return Mono.empty();
                            }
                        })
                        // defer = it will only execute when above portion execution is completed. It won't start otherwise
                        .then(Mono.defer(() -> {
                            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                    .header("X-User-ID", finalUserId)
                                    .build();
                            return chain.filter(exchange.mutate().request(mutatedRequest).build());
                        }));
            }
            return chain.filter(exchange);
        }
    
        /**
         * Extracts the user information from the incoming bearer token.
         *
         * <p>The method removes the "Bearer " prefix, parses the JWT, and reads the
         * claims from the token. These claims are then used to build a registration
         * payload that can be forwarded to the user service. In short, this is the
         * place where the gateway turns an incoming access token into a user object
         * that can be saved in the downstream service.</p>
         *
         * @param token the authorization header value, usually in the form
         *              "Bearer <jwt>"
         * @return a registration request generated from the JWT claims, or null when
         * the token cannot be parsed
         */
        private RegisterRequest getUserDetails(String token) {
            try {
                String tokenWithoutBearer = token.replace("Bearer ", "").trim();
                SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
                JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
                // The token claims are used as the main identity source; a placeholder email is set for the registration payload.
                return new RegisterRequest(claims, "dummy@123123");
    
            } catch (Exception exception) {
                String message = "GATEWAY-SERVICE:: " + exception.getClass().getSimpleName() + ": " + exception.getMessage();
                logger.error("getUserDetails Exception occurred: {}", message);
                return null;
            }
        }
    }
    ```

    

        </details>

- <details>
      <summary>🔽🔼 **UserService.java** - Gateway-side helper service for communicating with the downstream user service</summary>

  ```java
  import com.sg.fitness.gateway.dto.RegisterRequest;
  import com.sg.fitness.gateway.dto.UserResponse;
  import lombok.extern.slf4j.Slf4j;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.stereotype.Service;
  import org.springframework.web.reactive.function.client.WebClient;
  import org.springframework.web.reactive.function.client.WebClientResponseException;
  import reactor.core.publisher.Mono;
  
  /**
   * Gateway-side helper service for communicating with the downstream user service.
   *
   * <p>This class wraps the HTTP calls used by the gateway to validate whether a
   * user already exists and to register a new user when the JWT identity is not
   * yet known to the user service. In simple terms, it acts as the gateway's
   * bridge to the user-management backend.</p>
   */
  @Service
  @Slf4j
  public class UserService {
  
      @Autowired
      private WebClient userServiceWebClient;
  
      /**
       * Checks whether a user already exists in the downstream user service.
       *
       * <p>This method sends a request to the user service validation endpoint and
       * expects a boolean result. In simple terms, it answers the question: "Does
       * this user already exist in the system?" If the user does not exist, the
       * downstream service returns 404, which is converted into a readable runtime
       * exception so the gateway can handle the situation clearly.</p>
       *
       * @param userId the user identifier that must be validated
       * @return a reactive boolean that resolves to true when the user exists and
       * false when the user is missing
       */
      public Mono<Boolean> validateUser(String userId) {
          log.info("Calling User Validation API for userId: {}", userId);
          return userServiceWebClient.get()
                  .uri("/api/users/{userId}/validate", userId)
                  .retrieve()
                  .bodyToMono(Boolean.class)
                  .onErrorResume(WebClientResponseException.class, e -> {
                      if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                          return Mono.error(new RuntimeException("User Not Found: " + userId));
                      else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                          return Mono.error(new RuntimeException("Invalid Request: " + userId));
                      return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                  });
      }
  
      /**
       * Registers a new user with the downstream user service.
       *
       * <p>This method posts the incoming registration payload to the user service
       * registration endpoint and waits for the created user details in return.
       * In simple terms, it is the gateway-side "create user" call that forwards
       * the registration request to the correct service and then maps the response
       * or error into a meaningful runtime error when something goes wrong.</p>
       *
       * @param registerRequest the user registration payload that should be sent to
       *                        the user service
       * @return a reactive user response returned by the downstream registration API
       */
      public Mono<UserResponse> registerUser(RegisterRequest registerRequest) {
          log.info("Calling User Registration API for email: {}", registerRequest.getEmail());
          return userServiceWebClient.post()
                  .uri("/api/users/register")
                  .bodyValue(registerRequest)
                  .retrieve()
                  .bodyToMono(UserResponse.class)
                  .onErrorResume(WebClientResponseException.class, e -> {
                      if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                          return Mono.error(new RuntimeException("Bad Request: " + e.getMessage()));
                      else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                          return Mono.error(new RuntimeException("Internal Server Error: " + e.getMessage()));
                      return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                  });
      }
  
  }
  ```

  

      </details>





**Integrating in User-Service**

 In User **validate** api (`/api/users/{{userId}}/validate`), we need to check **if the keycloak id is present** (that means keycloak server and our postgres db is in sync)

```java
public Boolean existByUserId(String userId) {
    log.info("Calling User Validation API for userId: {}", userId);
    // return userRepo.existsById(userId);
    return userRepo.existsByKeycloakId(userId);
}
```







