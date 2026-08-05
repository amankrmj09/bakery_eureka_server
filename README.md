# 🧁 Bakery Eureka Server

![Java](https://img.shields.io/badge/Java-25-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.15-brightgreen.svg)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.0.3-blue.svg)

Welcome to the **Bakery Eureka Server**, the central Service Discovery and Registry server for the Bakery Microservice Platform. Built with Spring Boot and Spring Cloud Netflix Eureka Server, it enables dynamic service registration, heartbeats, health monitoring, and routing lookup for all microservices in the platform.

---

## 📑 Table of Contents
- [Features](#-features)
- [Folder Structure](#-folder-structure)
- [Configuration & Environment](#-configuration--environment)
- [API Reference & Endpoints](#-api-reference--endpoints)
- [How to Run](#-how-to-run)
- [Docker Support](#-docker-support)
- [Related Links](#-related-links)

---

## ✨ Features
- **Dynamic Service Registry:** Allows microservices to dynamically register and discover each other without hardcoding hostnames/ports.
- **Heartbeat & Health Monitoring:** Monitors microservice instance status via peer heartbeats and automatically evicts dead instances.
- **Web Dashboard:** Interactive Spring Cloud Eureka UI to inspect registered application instances, status, and metadata.
- **Multi-Profile Support:** Pre-configured profiles for Local Development (`dev`), Docker (`docker`), and Production (`prod`).
- **Spring Cloud Config Integration:** Fetches centralized application configuration from the Config Server.

---

## 📁 Folder Structure

```text
bakery_eureka_server/
├── .env
├── .env.example
├── .gitattributes
├── .gitignore
├── API_REFERENCE.md
├── Dockerfile
├── HELP.md
├── README.md
├── REAMDE.md
├── build.gradle.kts
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── blubugtech/
    │   │           └── BakeryEurekaServerApplication.java
    │   └── resources/
    │       ├── application.yml
    │       ├── application-dev.yml
    │       ├── application-docker.yml
    │       ├── application-prod.yml
    │       ├── logback-spring.xml
    │       ├── static/
    │       └── templates/
    └── test/
        └── java/
            └── com/
                └── blubugtech/
                    └── BakeryEurekaServerApplicationTests.java
```

---

## ⚙️ Configuration & Environment

The application uses profile-based configuration (`application.yml`, `application-dev.yml`, `application-docker.yml`, `application-prod.yml`).

### Key Port Settings:
- **Local Dev Default Port:** `8082` (configured in `application.yml` / `application-dev.yml`)
- **Docker Default Port:** `8761` (configured in `application-docker.yml`)

### Environment Variables (`.env`):
| Variable | Description | Default |
| --- | --- | --- |
| `ACTIVE_PROFILE` | Active Spring profile (`dev`, `docker`, `prod`) | `dev` |
| `SERVER_PORT` | Server HTTP listening port | `8082` |
| `CONFIG_SERVER_URL` | Spring Cloud Config Server URL | `http://localhost:8081` |
| `EUREKA_HOSTNAME` | Hostname for Eureka registration | `localhost` |
| `EUREKA_URL` | Service URL for default zone | `http://localhost:8082/eureka/` |

---

## 🌐 API Reference & Endpoints

> [!NOTE]
> Detailed API documentation is available in [API_REFERENCE.md](API_REFERENCE.md).

### Summary of Key Endpoints:
- **Web UI Dashboard:** `GET /` — Eureka Server dashboard displaying registered instances and status.
- **Service Registration & REST API:** `POST /eureka/apps/{appID}`, `GET /eureka/apps`, `DELETE /eureka/apps/{appID}/{instanceID}`, etc.
- **Actuator Health & Metrics:** `GET /actuator/health`, `GET /actuator/prometheus`.

---

## 🚀 How to Run

### Prerequisites
- **JDK 25** (or standard Java toolchain matching build configuration)
- **Config Server** (Optional/Recommended, running on `http://localhost:8081`)

### 1. Configure Environment
Copy `.env.example` to `.env` and adjust properties if needed:
```bash
cp .env.example .env
```

### 2. Run with Gradle
```bash
# Windows
.\gradlew.bat bootRun

# Linux / macOS
./gradlew bootRun
```

### 3. Access Eureka Dashboard
Open your browser and navigate to:
`http://localhost:8082/` (or `http://localhost:8761/` if running in docker mode)

---

## 🐳 Docker Support

To build and run using Docker:

```bash
# Build image
docker build -t bakery-eureka-server .

# Run container on port 8761
docker run -d -p 8761:8761 --name bakery-eureka-server -e ACTIVE_PROFILE=docker bakery-eureka-server
```

---

## 🔗 Related Links
- [Parent Repository](https://github.com/amankrmj09/Blu_s_Bakery)
- [API Reference](./API_REFERENCE.md)
- [Main Platform README](../README.md)
