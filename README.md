# Job Application Tracker

A Spring Boot REST API for managing job application records.

## Features

- Create job applications
- View all applications
- View an application by ID
- Update applications
- Delete applications
- Validate required fields
- Return appropriate HTTP status codes

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Maven

## Architecture

Client → Controller → Service → Repository → Database

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/applications` | Get all applications |
| GET | `/applications/{id}` | Get one application |
| POST | `/applications` | Create an application |
| PUT | `/applications/{id}` | Update an application |
| DELETE | `/applications/{id}` | Delete an application |

## Example Request

```json
{
  "company": "Microsoft",
  "jobTitle": "Software Engineer",
  "status": "Applied"
}
```

## Running the Project

1. Open the project in VS Code.
2. Run `JobApplicationTrackerApplication.java`.
3. Test the API at `http://localhost:8080/applications`.

## Future Improvements

- Add PostgreSQL
- Add search and filtering
- Add authentication
- Add automated tests
- Deploy the application
