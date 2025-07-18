# Tiny API Gateway

**© 2025 Jyotirmay Gupta**

**Project:** Tiny API Gateway  
**Description:** This is a personal Spring Cloud API Gateway implementation by Jyotirmay Gupta. It serves as a reactive, non-blocking entry point for routing and filtering HTTP requests to microservices using Spring Cloud Gateway and WebFlux.

The project demonstrates key cloud-native patterns like service discovery, centralized routing, and non-blocking request handling in microservice environments.

Licensed under the Apache License Version 2.0. See [LICENSE](LICENSE) for more details.

---

## 🧭 Overview

**Tiny API Gateway** acts as the main gateway for routing HTTP requests to backend services dynamically registered via **Eureka service discovery**.

It uses [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) with **Spring WebFlux** for fully non-blocking, reactive request handling. The gateway dynamically discovers services by their service IDs and forwards traffic accordingly.

---

## ✅ Features

- Reactive, non-blocking routing using Spring WebFlux
- Dynamic service discovery via Eureka
- Lightweight gateway setup with minimal configuration
- Execution time logging filter for all requests
- Centralized traffic entry point for microservices
- Easily extensible for rate-limiting, security, and filtering

---

## 🌐 Example Routes

| Path                             | Description                                        |
|----------------------------------|----------------------------------------------------|
| `/user-service/**`              | Routes to the registered `user-service`           |
| `/order-service/**`             | Routes to the registered `order-service`          |
| `/product-service/api/v1/**`    | Routes to `product-service` with path filtering   |

> 🚀 Services must be registered in Eureka with matching service names.

---

## 🛠 Technologies Used

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka Client
- Spring WebFlux (Reactive)
- SLF4J (for logging execution time)

---

## 🚀 How to Run

1. **Clone this repository:**

   ```bash
   git clone https://github.com/your-username/tiny-api-gateway.git
   cd tiny-api-gateway
   ```
2. Update application.yml with your Eureka server:
```
yaml
eureka:
    client:
        service-url:
            defaultZone: http://localhost:8761/eureka/
```
3. Build and run the API Gateway:
```bash
mvn clean spring-boot:run
```
4. Access services via the gateway:
```bash
curl http://localhost:8080/user-service/api/users
```
---

## Request Logging Filter
All incoming requests are timed using a custom global filter.

Example log:

```pgsql
INFO  - Request to [/user-service/api/users] completed in 35 ms with signal [ON_COMPLETE]
ERROR - Request to [/order-service/api/orders] failed after 12 ms with error: Connection refused
```

---

## 📝 Notes
- Eureka service registry must be running at the configured address.

- Target microservices must be registered with Eureka under their spring.application.name.

- Logical service name routing (like /user-service/**) is handled dynamically without static URI definitions.

- Add authentication, rate limiting, or circuit breakers as needed.

---


## 📄 License
Licensed under the Apache License Version 2.0. See the LICENSE file for details.

---

*Created by Jyotirmay Gupta — 2025*

