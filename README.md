# Gym Management API

REST API to manage a gym: students, teachers, plans, monthly payments, daily accounting and e-mail notifications.

I built this project to help small local businesses in my city (Milagres, Bahia, Brazil). The backend is finished. A React front end is planned.

## Features

- **Students**: create, update, list, search by id, check financial situation, and register body measurements.
- **Teachers**: create, update, list, search by id and delete.
- **Plans**: create, update, list, search by id and delete.
- **Monthly payments**: register and list payments. A scheduled job checks late payments (`MonthlyPaymentValidation`).
- **Daily accounting**: list daily income and search by period.
- **E-mail**: send e-mails to students.
- **Security**: authentication with Spring Security, OAuth2 and JWT.
- **API docs**: Swagger UI (springdoc-openapi).

## Tech stack

| Area | Technology |
| --- | --- |
| Language | Java 11 |
| Framework | Spring Boot (Web, Data JPA, Validation, Security, Mail) |
| Security | Spring Security, OAuth2, JWT |
| Database | PostgreSQL |
| Mapping | ModelMapper, Lombok |
| Tests | JUnit 5, Spring Boot Test, Awaitility |
| Docs | springdoc-openapi (Swagger UI) |
| Build / Deploy | Maven, Docker |

## Project structure

```
src/main/java/com/management/gym
├── config        # security (OAuth2 / JWT), Swagger, web config
├── controller    # REST endpoints
├── service       # business rules
├── repository    # Spring Data JPA
├── model         # entities and DTOs
├── enums         # payment form, financial status, sex
├── schedule      # scheduled job for monthly payments
└── common        # ModelMapper config and utilities
```

## Main endpoints

| Resource | Base path | Operations |
| --- | --- | --- |
| Student | `/student` | GET, GET `/by-id`, GET `/situation`, GET `/measurements`, POST, PUT, PUT `/measurements` |
| Teacher | `/teacher` | GET, GET `/by-id`, POST, PUT, DELETE |
| Plan | `/plan` | GET, GET `/by-id`, POST, PUT, DELETE |
| Monthly payment | `/monthly-payment` | GET, POST |
| Daily accounting | `/daily-accounting` | GET, GET `/by-period` |
| E-mail | `/email` | POST |

The full list is in Swagger UI after you run the project.

## How to run

**Requirements:** Java 11, Maven and a PostgreSQL database.

1. Clone the repository:
   ```bash
   git clone https://github.com/GabrielSantosBa/Manangment-gym.git
   cd Manangment-gym
   ```
2. Create a PostgreSQL database and set the connection (url, user and password) in `src/main/resources/application.properties`.
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Open Swagger UI: `http://localhost:8080/swagger-ui.html` (default port; change it in `application.properties` if needed).

**Run the tests:**
```bash
./mvnw test
```

**Docker:** a `dockerfile` is included in the project.

## What I practiced

- Layered architecture (controller, service, repository) and DTOs
- Business rules, validation and error handling
- Authentication and authorization with OAuth2 and JWT
- Unit and controller tests with JUnit and builders
- Scheduled jobs and e-mail sending

## Author

**Gabriel Bahia**, Java Backend Developer.
LinkedIn: https://www.linkedin.com/in/gabriel-santosba/
