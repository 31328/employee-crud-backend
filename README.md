# Employee REST Backend

Backend implementing CRUD operations over a dataset of 96 fictional employees. The system exposes a REST API, enforces validation rules, maps data to a relational schema, and maintains strict separation between DTOs and entities. Preloading logic inserts all employees at startup when the table is empty.

## Features

- CRUD operations over employee records
- DTO validation using Jakarta Validation
- JPA entity mapping with unique email constraint
- Exception handling through a centralized controller advice
- DTO–entity conversion layer
- Automatic preload of 96 employees
- Query for maximum salary stored in the database

## Architecture Overview

### DTO Layer

**EmployeeDto**  
Carries request and response payloads. Includes validation rules for name, surname, email, position, location, and salary. Ensures only valid data reaches the service layer.

### Entity Layer

**EmployeeEntity**  
Persistent model mapped to the `employees` table. Enforces storage-level constraints including length limits and unique email.

### Converter

**EmployeeConverter**  
Transforms between `EmployeeDto` and `EmployeeEntity`. Maintains decoupling between API-facing and persistence-facing models.

### Service Layer

**EmployeeServiceImpl**  
Implements business logic:

- Fetch all employees
- Fetch employee by ID
- Insert new employee
- Update existing employee
- Delete single employee
- Delete all employees
- Query maximum salary

Startup routine loads 96 fictional employees if the database is empty. Duplicate email detection is enforced on insert and update operations. Missing records trigger custom exceptions.

### Controller Layer

**EmployeeController**  
REST interface under `/employee`:

- `GET /employee` → list all
- `GET /employee/{id}` → get by ID
- `POST /employee` → create
- `PUT /employee/{id}` → update
- `DELETE /employee/{id}` → delete
- `DELETE /employee/removeAll` → delete all
- `GET /employee/maximumSalary` → retrieve highest salary

Incoming DTOs are validated. Validation failures produce custom error responses.

### Exception Handling

**ErrorsController**  
Maps custom exceptions to HTTP statuses:

- `InstanceUndefinedException` → 412
- `DataNotValidatedException` → 400
- `DuplicateEmailException` → 409

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- Maven
- H2 or external SQL database

## Running

1. Clone repository
2. Build with Maven
3. Execute Spring Boot application
4. Access REST endpoints under `/employee`

## Purpose

Demonstrates clean REST design, structured validation, service-layer logic, JPA persistence, and startup initialization with a prepopulated employee dataset.
