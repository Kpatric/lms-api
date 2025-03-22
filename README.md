```markdown
# 🚀 Loan Management System (LMS)

Loan Management System (LMS) provides micro-loan solutions, seamlessly integrated with a Bank's Core Banking System (CBS) and a Scoring Engine using Spring Boot.

---

## 📌 Technologies & Tools

- Java 17+
- Spring Boot 3.x
- PostgreSQL
- Flyway (Database migrations)
- SOAP Integration (CBS, KYC)
- REST APIs
- Docker & Docker Compose
- Ngrok (local external testing)

---

## ✅ Getting Started

### Prerequisites

- Java 17+
- Docker & Docker Compose
- Ngrok ([download](https://ngrok.com/download))
- Gradle
- Git

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/lms.git
    cd lms
    ```

2. Set up the environment variables:
    ```bash
    cp src/main/resources/application-dev.yml src/main/resources/application.yml
    ```

3. Update the `application.yml` file with your database and scoring engine configurations.

4. Build the project:
    ```bash
    ./gradlew build
    ```

5. Run the database migrations:
    ```bash
    ./gradlew flywayMigrate
    ```

6. Start the application:
    ```bash
    ./gradlew bootRun
    ```

### Running with Docker

1. Build the Docker image:
    ```bash
    docker build -t lms .
    ```

2. Start the services using Docker Compose:
    ```bash
    docker-compose up
    ```

### Ngrok Setup

1. Start Ngrok to expose your local server:
    ```bash
    ngrok http 8585
    ```

2. Update the `callback-url` in `application.yml` with the Ngrok URL.

---

## 🔨 Project Structure

src/main/java/com/lms/api
│
├── auth             # Authentication and security configurations
├── core             # Core shared components and exceptions
├── loan             # Loan-related endpoints and logic
│   ├── controller   # REST API endpoints
│   ├── service      # Business logic services
│   ├── repository   # Data repositories
│   └── model        # JPA Entities
└── integration      # External service integrations (CBS, Scoring Engine)

---

## 📄 API Documentation

API documentation is available at `/api-docs-ui.html` once the application is running.

---

## 🧪 Running Tests

To run the tests, use the following command:
```bash
./gradlew test
```

---

## 📦 Deployment

For deployment, ensure all environment variables are set correctly and use the following command to build and run the application:
```bash
./gradlew bootJar
java -jar build/libs/lms-0.0.1-SNAPSHOT.jar
```

---
