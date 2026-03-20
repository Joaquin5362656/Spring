# Book Management API (Spring Boot + Docker)

This is a RESTful API built with **Spring Boot 3.5**, designed to manage a book catalog. The project has evolved from a local H2 setup to a fully containerized architecture using **PostgreSQL**.

---

## Tech Stack
* **Java 23** (JDK 23)
* **Spring Boot** (Web, Data JPA, DevTools)
* **PostgreSQL** (Production-ready database)
* **Docker & Docker Compose** (Containerization)
* **Swagger/OpenAPI 3** (Interactive Documentation)
* **JUnit 5** (Integration & Unit Testing)

---

## Getting Started

To run this project locally, you only need **Docker** and **Docker Compose** installed. You don't need to manually configure the database.

### 1. Clone the repository
```bash
git clone <your-repo-url>
cd <project-folder>
```
---
### 2. Build the application
Before running the application, Before running the containers, package the application into a JAR file:
```bash
./mvnw clean package -DskipTests
```
---
### 3. Run with Docker Compose
Spin up the application and the database simultaneously:
```bash
docker-compose up --build
```   
The application will be accessible at:
```
http://localhost:8080
```
---
## API Documentation (Swagger)
Once the app is running, you can explore and test all endpoints through the interactive Swagger UI:
```
http://localhost:8080/swagger-ui/index.html
```
---
## API Endpoints
| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/books` | List all books |
| GET | `/api/books/{id}` | Find a book by ID |
| POST | `/api/books` | Create a new book |
| PUT | `/api/books` | Update an existing book |
| DELETE | `/api/books/{id}` | Remove a book by ID |
| DELETE | `/api/books` | Delete all books |
---
## Database Configuration

The application is configured to run with **PostgreSQL**.

- **Driver**: `org.postgresql.Driver`
- **Default port**: `5432`
- **Time zone**: forced to `UTC` using VM Options and environment variables in Docker, to maintain consistency between Windows (local) and Linux (Docker/Cloud).
##  Testing

The project includes:

- Integration tests for the Controller layer
- Unit tests for the business logic

To run the tests:

```bash
./mvnw test