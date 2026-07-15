# 🧁 Eureka Server

![Java](https://img.shields.io/badge/Java-21%2B-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)

Welcome to the **Eureka Server**, a core component of the Shah's Bakery Microservice Platform.

## 📑 Table of Contents
- [Features](#-features)
- [Folder Structure](#-folder-structure)
- [Dependencies](#-dependencies)
- [Endpoints](#-endpoints)
- [How to Run](#-how-to-run)
- [Related Links](#-related-links)

## ✨ Features
- Centralized service registry and dynamic service discovery.
- Health monitoring and status tracking of all registered microservices.
- Load-balancing support when used with Spring Cloud LoadBalancer.

## 📁 Folder Structure
The main `src/main/java` directory is organized as follows:
```text
src/
└── main/
    └── java/.../bakery_eureka_server/
        └── BakeryEurekaServerApplication.java # The main Spring Boot entry point with @EnableEurekaServer.
```

## 🛠️ Dependencies
- **Framework:** Spring Boot, Spring Cloud Netflix Eureka
- **Key Modules:** Eureka Server

## 🌐 Endpoints
> [!NOTE]
> The Eureka Server provides an internal dashboard rather than REST endpoints for typical client consumption.

- `GET /` - Opens the Eureka Server dashboard (UI) to view registered instances.

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/amankrmj01/bakery_eureka_server.git
   cd bakery_eureka_server
   ```

2. **Configure Environment:**
   Ensure your `.env` or `application.yml` properties are configured correctly.

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

## 🔗 Related Links
- [Main Platform README](../README.md)
