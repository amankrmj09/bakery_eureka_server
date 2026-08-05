# 📖 API Reference - Bakery Eureka Server

This document details the REST endpoints, Web Dashboard interface, and Actuator monitoring endpoints exposed by the **Bakery Eureka Server**.

---

## 📌 Overview

- **Service Name:** `bakery-eureka-server`
- **Default Local Port:** `8082` (Dev Profile) / `8761` (Docker Profile)
- **Base URL:** `http://localhost:8082` (Local) or `http://localhost:8761` (Docker)
- **Default Eureka Context Path:** `/eureka/`
- **Supported Content Types:** `application/json`, `application/xml`

---

## 🖥️ Web Dashboard

The Eureka Server provides an interactive user interface to view all currently registered microservices, active instances, server status, memory usage, and lease information.

| Method | Endpoint | Description | Auth Required |
| --- | --- | --- | --- |
| `GET` | `/` | Renders the Eureka Server HTML dashboard. | No |

---

## 🔄 Eureka Client REST API

Microservices interact with the Eureka Server using standard Eureka REST API operations for registration, heartbeat renewals, status overrides, and service discovery lookups.

> [!NOTE]
> Requests to the REST API can accept and return JSON or XML depending on the `Accept` and `Content-Type` headers (`application/json` or `application/xml`).

### 1. Register a New Service Instance
Registers a microservice instance with the Eureka Server.

- **HTTP Method:** `POST`
- **Endpoint:** `/eureka/apps/{appID}`
- **Path Parameters:**
  - `appID` *(string)*: Name of the registered application (e.g., `BAKERY-AUTH-SERVICE`, `BAKERY-ORDER-SERVICE`).
- **Request Body Example:**
  ```json
  {
    "instance": {
      "hostName": "localhost",
      "app": "BAKERY-AUTH-SERVICE",
      "ipAddr": "127.0.0.1",
      "status": "UP",
      "port": { "$": 8083, "@enabled": "true" },
      "dataCenterInfo": { "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo", "name": "MyOwn" }
    }
  }
  ```
- **Response Body Example:**
  ```json
  {} // 204 No Content
  ```

### 2. De-register a Service Instance
Removes a microservice instance from the registry during graceful shutdown.

- **HTTP Method:** `DELETE`
- **Endpoint:** `/eureka/apps/{appID}/{instanceID}`
- **Path Parameters:**
  - `appID` *(string)*: Application name.
  - `instanceID` *(string)*: Unique instance ID (e.g., `localhost:bakery-auth-service:8080`).
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {} // 200 OK (Empty response)
  ```

### 3. Send Heartbeat (Renewal)
Periodically called by microservice clients (typically every 30 seconds) to inform Eureka that the instance is still alive.

- **HTTP Method:** `PUT`
- **Endpoint:** `/eureka/apps/{appID}/{instanceID}`
- **Query Parameters:**
  - `status` *(string)*: Updated instance status (e.g., `UP`, `DOWN`).
  - `lastDirtyTimestamp` *(long)*: Timestamp of client configuration changes.
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {} // 200 OK (Empty response)
  ```

### 4. Query All Registered Applications
Retrieves the full registry of all registered applications and their instances.

- **HTTP Method:** `GET`
- **Endpoint:** `/eureka/apps`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "applications": {
      "versions__delta": "1",
      "apps__hashcode": "UP_1_",
      "application": [
        {
          "name": "BAKERY-AUTH-SERVICE",
          "instance": [
            {
              "instanceId": "localhost:bakery-auth-service:8083",
              "hostName": "localhost",
              "app": "BAKERY-AUTH-SERVICE",
              "ipAddr": "127.0.0.1",
              "status": "UP",
              "port": {
                "$": 8083,
                "@enabled": "true"
              }
            }
          ]
        }
      ]
    }
  }
  ```

### 5. Query Specific Application
Retrieves all instances associated with a specific application name.

- **HTTP Method:** `GET`
- **Endpoint:** `/eureka/apps/{appID}`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "application": {
      "name": "BAKERY-AUTH-SERVICE",
      "instance": [
        {
          "instanceId": "localhost:bakery-auth-service:8083",
          "hostName": "localhost",
          "app": "BAKERY-AUTH-SERVICE",
          "ipAddr": "127.0.0.1",
          "status": "UP",
          "port": {
            "$": 8083,
            "@enabled": "true"
          }
        }
      ]
    }
  }
  ```

### 6. Query Specific Instance
Retrieves details for a specific instance under an application.

- **HTTP Method:** `GET`
- **Endpoint:** `/eureka/apps/{appID}/{instanceID}`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "instance": {
      "instanceId": "localhost:bakery-auth-service:8083",
      "hostName": "localhost",
      "app": "BAKERY-AUTH-SERVICE",
      "ipAddr": "127.0.0.1",
      "status": "UP",
      "port": {
        "$": 8083,
        "@enabled": "true"
      }
    }
  }
  ```

### 7. Query Instance by Instance ID
Queries instance details globally across all registered applications using the instance ID.

- **HTTP Method:** `GET`
- **Endpoint:** `/eureka/instances/{instanceID}`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "instance": {
      "instanceId": "localhost:bakery-auth-service:8083",
      "hostName": "localhost",
      "app": "BAKERY-AUTH-SERVICE",
      "ipAddr": "127.0.0.1",
      "status": "UP",
      "port": {
        "$": 8083,
        "@enabled": "true"
      }
    }
  }
  ```

### 8. Update Instance Status (Status Override)
Manually changes an instance status (e.g., taking an instance `OUT_OF_SERVICE` for maintenance).

- **HTTP Method:** `PUT`
- **Endpoint:** `/eureka/apps/{appID}/{instanceID}/status`
- **Query Parameters:**
  - `value` *(required string)*: Target status (`UP`, `DOWN`, `STARTING`, `OUT_OF_SERVICE`, `UNKNOWN`).
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {} // 200 OK (Empty response)
  ```

### 9. Delete Status Override
Removes a manual status override, reverting the status back to client heartbeat reports.

- **HTTP Method:** `DELETE`
- **Endpoint:** `/eureka/apps/{appID}/{instanceID}/status`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {} // 200 OK (Empty response)
  ```

### 10. Query Instances by VIP Address
Finds active instances by Virtual IP (VIP) address.

- **HTTP Method:** `GET`
- **Endpoint:** `/eureka/vips/{vipAddress}`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "applications": {
      "versions__delta": "1",
      "apps__hashcode": "UP_1_",
      "application": [
        {
          "name": "BAKERY-AUTH-SERVICE",
          "instance": [
            {
              "instanceId": "localhost:bakery-auth-service:8083",
              "hostName": "localhost",
              "app": "BAKERY-AUTH-SERVICE",
              "ipAddr": "127.0.0.1",
              "status": "UP",
              "port": {
                "$": 8083,
                "@enabled": "true"
              },
              "vipAddress": "bakery-auth-service"
            }
          ]
        }
      ]
    }
  }
  ```

### 11. Query Instances by Secure VIP Address
Finds active instances by Secure Virtual IP (SVIP) address.

- **HTTP Method:** `GET`
- **Endpoint:** `/eureka/svips/{svipAddress}`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "applications": {
      "versions__delta": "1",
      "apps__hashcode": "UP_1_",
      "application": [
        {
          "name": "BAKERY-AUTH-SERVICE",
          "instance": [
            {
              "instanceId": "localhost:bakery-auth-service:8083",
              "hostName": "localhost",
              "app": "BAKERY-AUTH-SERVICE",
              "ipAddr": "127.0.0.1",
              "status": "UP",
              "port": {
                "$": 8083,
                "@enabled": "true"
              },
              "secureVipAddress": "bakery-auth-service-secure"
            }
          ]
        }
      ]
    }
  }
  ```

---

## 📊 Spring Boot Actuator Endpoints

The Eureka Server incorporates `spring-boot-starter-actuator` for operational telemetry.

| Method | Endpoint | Description | Response Content-Type |
| --- | --- | --- | --- |
| `GET` | `/actuator/health` | Service health status check | `application/json` |
| `GET` | `/actuator/prometheus` | Micrometer Prometheus metrics export | `text/plain` |

### 1. Actuator Health
Service health status check.

- **HTTP Method:** `GET`
- **Endpoint:** `/actuator/health`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```json
  {
    "status": "UP",
    "components": {
      "discoveryComposite": {
        "description": "Discovery Client not initialized",
        "status": "UNKNOWN",
        "components": {
          "discoveryClient": {
            "description": "Discovery Client not initialized",
            "status": "UNKNOWN"
          }
        }
      },
      "diskSpace": {
        "status": "UP",
        "details": {
          "total": 510427901952,
          "free": 221764648960,
          "threshold": 10485760,
          "exists": true
        }
      },
      "ping": {
        "status": "UP"
      }
    }
  }
  ```

### 2. Actuator Prometheus
Prometheus metrics.

- **HTTP Method:** `GET`
- **Endpoint:** `/actuator/prometheus`
- **Request Body Example:**
  ```json
  {} // Empty body
  ```
- **Response Body Example:**
  ```text
  # HELP jvm_memory_used_bytes The amount of used memory
  # TYPE jvm_memory_used_bytes gauge
  jvm_memory_used_bytes{area="heap",id="G1 Survivor Space"} 0.0
  ```
