# The Java Bank

A full-featured banking backend application built with Spring Boot and MySQL.  
Now production-ready with JWT authentication, CI/CD via GitHub Actions, and Kubernetes deployment to GKE.

## Features

- User registration and login with JWT authentication
- Balance enquiry
- Credit and debit operations
- Funds transfer between accounts
- PDF statement generation and email sending

## Technologies Used

- Java 17 with Spring Boot
- Spring Security and JWT
- MySQL with JPA / Hibernate
- JavaMail and iText PDF
- Docker + AWS ECR
- Kubernetes (GKE)
- GitHub Actions for CI/CD

## Getting Started Locally

### Prerequisites

- Java 17+
- MySQL (local or container)
- Maven

### Configuration

Use the provided `src/main/resources/application.properties.example` as a template.  
Make sure to provide values for email credentials and JWT secrets.

### Run Locally
```bash
mvn spring-boot:run
```

### Swagger UI
Access the API documentation at:
```
http://localhost:8080/swagger-ui/index.html
```

## Usage
Use tools like **Postman** or **cURL** to test the endpoints.

## JWT Authentication

- `POST /api/user` → Register a new user
- `POST /api/user/login` → Receive JWT token
- Use `Authorization: Bearer <token>` for authenticated endpoints

## Docker

To build and run locally:

```bash
./mvnw clean package -DskipTests
docker build -t my-bank-app .
docker run -p 8080:8080 my-bank-app
```

## CI/CD Pipeline

### Requirements

Set the following secrets in your GitHub repository:

- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
- `GCP_CREDENTIALS` (base64-encoded GCP service account JSON)
- `MAIL_PASSWORD`
- `JWT_SECRET`

### Flow

The deployment process is triggered when changes are pushed to the `main` branch.

Steps involved:

1. Checkout the latest code.
2. Build the Spring Boot application.
3. Authenticate with AWS and log in to Amazon ECR.
4. Build and push the Docker image to ECR.
5. Authenticate with GCP using a service account.
6. Get access to the GKE cluster.
7. Restart the Kubernetes deployment to apply the new image.

## Kubernetes

### Included Manifest Files

- `mysql.yaml`: Deploys a MySQL instance along with its service.
- `deployment.yaml`: Defines the Spring Boot application deployment.
- `service.yaml`: Exposes the application via a LoadBalancer on port 80.

### Secrets Injection
Create K8s secret for email and JWT values:
```bash
kubectl create secret generic app-secrets \
  --from-literal=MAIL_PASSWORD=your-password \
  --from-literal=JWT_SECRET=your-secret
```



