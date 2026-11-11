# Microservices-Based Game Store

A backend e-commerce application for an online game store, built using **Spring Boot and Microservices Architecture**. The system is divided into independent services, each responsible for a specific business functionality, enabling scalability, maintainability, and independent development.

## System Architecture

### Service Execution Sequence

The application follows the following service startup and request processing sequence:

**A. Service Initialization**

1. **Eureka Server:** Starts first and initializes the service registry.
2. **RabbitMQ:** Initializes the message broker and prepares the messaging infrastructure.
3. **User Service:** Starts and registers itself with Eureka.
4. **Game Service:** Starts and registers itself with Eureka.
5. **Cart Service:** Starts and registers itself with Eureka.
6. **API Gateway:** Starts, registers with Eureka (if configured), and becomes the entry point for client requests.

**B. Request Processing Flow**

1. The client sends an HTTP request to the API Gateway.
2. The API Gateway identifies the destination service based on the configured routing rules.
3. Eureka provides service discovery when dynamic service lookup is configured.
4. The API Gateway forwards the request to the appropriate microservice.
5. The target microservice processes the request and interacts with its required resources.
6. If asynchronous communication is required, the service publishes a message to RabbitMQ.
7. The receiving service consumes and processes the message.
8. The response is returned to the client through the API Gateway for synchronous HTTP requests.


## Microservices

The project consists of six main services:

### 1. User Service
Responsible for user-related operations, including:
- User registration.
- User authentication and authorization.
- Managing user information.

### 2. Game Service
Responsible for managing the game catalog:
- Adding games.
- Retrieving game details.
- Managing game information and availability.

### 3. Cart Service
Responsible for shopping cart operations:
- Adding games to the cart.
- Removing games from the cart.
- Retrieving cart contents.
- Managing shopping cart data.

### 4. Eureka Service (Service Discovery)
Provides service discovery and registration:
- Registers microservices.
- Maintains a registry of available services.


### 5. API Gateway
Acts as the single entry point for client requests:
- Routes requests to the appropriate microservices.
- Centralizes API access.
- Simplifies communication between clients and backend services.

### 6. RabbitMQ Service (Message Broker)
Supports asynchronous communication between microservices:
- Enables message-based communication.
- Reduces direct dependencies between services.


## Technologies & Tools

- **Java**
- **Spring Boot**
- **Spring Cloud**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **RabbitMQ**
- **Maven**
- **REST APIs**

## Project Structure

```text
microservices-game-store/
│
├── user-service/
│   ├── src/
│   └── pom.xml
│
├── game-service/
│   ├── src/
│   └── pom.xml
│
├── cart-service/
│   ├── src/
│   └── pom.xml
│
├── eureka-service/
│   ├── src/
│   └── pom.xml
│
├── gateway-service/
│   ├── src/
│   └── pom.xml
│
├── rabbitmq-service/
│   ├── src/
│   └── pom.xml
│
└── README.md
```

## Getting Started

### Prerequisites

Make sure you have installed:
- Java Development Kit (JDK).
- Maven.
- RabbitMQ.


### Installation

1. Clone the repository:

```bash
git clone https://github.com/USERNAME/REPOSITORY.git
```

2. Navigate to the project directory:

```bash
cd REPOSITORY
```

3. Configure the required application properties for each service.

4. Start RabbitMQ if your implementation requires it.

5. Run the Eureka Server.

6. Run the remaining microservices.

7. Run the API Gateway and access the application through its configured port.
