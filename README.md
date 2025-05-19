# Recruitment Pipeline API Backend

This is a Spring Boot-based backend REST API for managing candidates in a recruitment pipeline. It supports CRUD operations, filtering by application stage, pagination, sorting, and stage updates (for drag-and-drop UIs).

## 🚀 Tech Stack
- Java 21
- Spring Boot 3.x
- MongoDB
- Spring Data MongoDB
- Spring Web
- Springdoc OpenAPI (Swagger UI)

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

### 2. Configure MongoDB
- **Option 1**: Use [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
  - Create a free cluster
  - Whitelist your IP and get the connection URI
- **Option 2**: Run MongoDB locally

### 3. Update `application.properties`
```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/recruitment_db
```

### 4. Build and Run the Application
```bash
./mvnw spring-boot:run
```

---

## 📦 API Features

### CRUD Endpoints
- `POST /api/candidates` – Create a candidate
- `GET /api/candidates` – List candidates (supports pagination and sorting)
- `GET /api/candidates/{id}` – Get single candidate
- `PUT /api/candidates/{id}` – Update entire candidate
- `DELETE /api/candidates/{id}` – Delete candidate

### Additional
- `GET /api/candidates/stage/{stage}` – Filter by stage
- `PATCH /api/candidates/{id}/stage` – Drag-and-drop style stage update

### Swagger UI
Available at:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📝 Assumptions
- `referralStatus` and `assessmentStatus` are stored as boolean values (`true` / `false`).
- Valid `applicationStage` values include: `Applying Period`, `Screening`, `Interview`, `Test`.

---

## 📂 Project Structure
```
├── controller
├── model
├── repository
├── service
└── RecruitmentPipelineApplication.java
```
