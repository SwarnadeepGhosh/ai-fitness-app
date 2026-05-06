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
- Local Swagger URL : [http://localhost:8080/docs.html](http://localhost:8080/docs.html)
- 📘 API Details

| API                            | Method | Purpose          |
| ------------------------------ | ------ | ---------------- |
| `/api/users/register`          | POST   | Create user      |
| `/api/users/{userId}`          | GET    | Get user profile |
| `/api/users/{userId}/validate` | GET    | Validate user    |

| #    | API              | Method | Endpoint                     | Description            | Request                                                      | Response                                                     |
| ---- | ---------------- | ------ | ---------------------------- | ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | Register User    | POST   | /api/users/register          | Create a new user      | Body (RegisterRequest):`email*`, `password* (min 6)`, `keycloakId`, `firstName`, `lastName` | UserResponse: `{"id":"string","keycloakId":"string","email":"string","password":"string","firstName":"string","lastName":"string","createdAt":"date-time","updatedAt":"date-time"}` |
| 2    | Get User Profile | GET    | /api/users/{userId}          | Get user details       | Path:`userId* (string)`                                      | UserResponse : `{"id":"string","keycloakId":"string","email":"string","password":"string","firstName":"string","lastName":"string","createdAt":"date-time","updatedAt":"date-time"}` |
| 3    | Validate User    | GET    | /api/users/{userId}/validate | Check if user is valid | Path:`userId* (string)`                                      | `true`                                                       |



## 🏃 Activity Service

- Remote Swagger URL: 
- Local Swagger URL : [http://localhost:8080/docs.html](http://localhost:8080/docs.html)
- 📘 API Details





## 🔷 AI Service

- Remote Swagger URL: 
- Local Swagger URL : [http://localhost:8080/docs.html](http://localhost:8080/docs.html)
- 📘 API Details







