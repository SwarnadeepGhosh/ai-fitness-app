# AI-Powered Fitness Application

[Live Frontend Link]()

[Live Backend Swagger Link]()



<p align="center">
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
> 1. Exception handling
> 2. Context path





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



## Inter Service Communication

[Spring Framework Rest Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html) :

- The Spring Framework provides the following choices for making calls to REST endpoints:

  - [`RestClient`](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient) — synchronous client with a fluent API

  - [`WebClient`](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-webclient) — non-blocking, reactive client with fluent API

  - [`RestTemplate`](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-resttemplate) — synchronous client with template method API, now deprecated in favor of `RestClient`

  - [HTTP Service Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-service-client) — annotated interface backed by generated proxy

- We will use WebClient here. 



### `WebClient`

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
  
  
  # Saving Activity with saved api
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

  





## 🤖 AI Service

- Remote Swagger URL: 
- Local Swagger URL : [http://localhost:8080/docs.html](http://localhost:8080/docs.html)
- 📘 API Details







