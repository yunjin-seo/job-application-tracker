# Job Application Tracker

Track job applications with a Spring Boot application featuring a REST API and a simple web interface.

## Features

- Add, view, edit, and delete job applications
- List all applications or one application by ID
- Find applications based on status, company name, or job title
- Add, edit, and delete applications using a web page interface
- Show simple error messages
- Run tests for database searches and service code
- Keep application data after restarting the app

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
- Mockito
- HTML, CSS, JavaScript

## Architecture

Web page → Controller → Service → Repository → Database

## API Endpoints

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/applications` | Get all job applications | `200 OK` |
| GET | `/applications/{id}` | Get a job application by ID | `200 OK` |
| GET | `/applications?status={status}` | Filter job applications by status | `200 OK` |
| POST | `/applications` | Create a job application | `201 Created` |
| PUT | `/applications/{id}` | Update a job application | `200 OK` |
| DELETE | `/applications/{id}` | Delete a job application | `204 No Content` |
| GET | `/applications/search/job-title?jobTitle={jobTitle}` | Search job applications by job title | `200 OK` |
| GET | `/applications/search?company={company}` | Search job applications by company | `200 OK` |

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

We use a fake repository to test the service.
These tests verify how to add, find, edit, and delete applications.

Run all tests with:

```bash
./mvnw test
```


## Running the Project

Java 24 is required to run this project.

1. Open the project in VS Code.
2. Run `JobApplicationTrackerApplication.java`.
3. Open `http://localhost:8080` to add an application.
4. Open `http://localhost:8080/applications.html` to view the list.

## Data Storage

The app saves applications in a local H2 file.
The data stays after you restart the app.
Testing takes place using a different temporary database.
The `data/` folder is not uploaded to GitHub.

## Future Improvements

- Use PostgreSQL instead of H2
- Show results in pages and sort them
- Add tests for the controller
- Add API documentation
- Add user login
- Put the app online
