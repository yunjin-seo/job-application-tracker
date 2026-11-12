# Job Application Tracker

A Spring Boot REST API for managing job application records.

## Features

- Create job applications
- View all job applications
- View an job application by ID
- Filter job applications by status
- Update job applications
- Delete job applications
- Validate required fields
- Return appropriate HTTP status codes
- Return structured error responses
- Test repository queries with automated JPA tests

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Jakarta Bean Validation
- JUnit 5
- AssertJ

## Architecture

Client → Controller → Service → Repository → Database

## API Endpoints

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/applications` | Get all job applications | `200 OK` |
| GET | `/applications/{id}` | Get a job application by ID | `200 OK` |
| GET | `/applications?status={status}` | Filter job applications by status | `200 OK` |
| POST | `/applications` | Create a job application | `201 Created` |
| PUT | `/applications/{id}` | Update a job application | `200 OK` |
| DELETE | `/applications/{id}` | Delete a job application | `204 No Content` |

## Example Request and Response

### Create a Job Application

#### Request

```http
POST /applications
Content-Type: application/json
```

```json
{
  "company": "Microsoft",
  "jobTitle": "Software Engineer",
  "status": "APPLIED"
}
```

#### Response

```http
HTTP/1.1 201 Created
Content-Type: application/json
```

```json
{
  "id": 1,
  "company": "Microsoft",
  "jobTitle": "Software Engineer",
  "status": "APPLIED"
}
```
## Application Statuses

The following status values are supported:

- `APPLIED`
- `INTERVIEW`
- `OFFERED`
- `REJECTED`

## Error Responses


### Validation Error

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
  "message": "Company is required"
}
```

### Invalid Status

```http
HTTP/1.1 400 Bad Request
Content-Type: application/json
```

```json
{
  "message": "Invalid status value"
}
```

### Job Application Not Found

```http
HTTP/1.1 404 Not Found
Content-Type: application/json
```

```json
{
  "message": "Job application not found"
}
```
## Testing

Repository queries are tested using Spring Boot JPA tests and the H2 in-memory database.

The tests verify:

- Filtering job applications by status
- The number of returned applications
- The companies contained in the results

Run all tests with:

```bash
./mvnw test
```

## Running the Project

1. Open the project in VS Code.
2. Run `JobApplicationTrackerApplication.java`.
3. Test the API at `http://localhost:8080/applications`.

## Future Improvements

- Replace the H2 database with PostgreSQL
- Add pagination and sorting
- Add company and job title search
- Add service and controller tests
- Add OpenAPI/Swagger documentation
- Add authentication and authorization
- Deploy the application
