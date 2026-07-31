package com.jin.jobapplicationtracker;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HomeController {
    private final JobApplicationService service;

    public HomeController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping("/about")
    public String about() {
        return "About Page";
    }

    @GetMapping("/company/{name}")
    public String company(@PathVariable String name) {
        return "Company: " + name;
    }

    @GetMapping("/application")
    public JobApplication getApplication() {

        return new JobApplication(
                "Google",
                "Software Engineer",
                "Applied");

    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<JobApplication> application(@PathVariable int id) {

        JobApplication application = service.getApplication(id);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(application);
    }

    @GetMapping("/applications")
    public List<JobApplication> getApplications() {
        return service.getApplications();
    }

    @PostMapping("/applications")
    public ResponseEntity<JobApplication> createApplication(@Valid @RequestBody JobApplication application) {
    
        JobApplication createdApplication = service.addApplication(application);

        return ResponseEntity
                .status(201)
                .body(createdApplication);
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable int id,
            @Valid @RequestBody JobApplication application) {

        JobApplication updatedApplication = service.updateApplication(id, application);

        if (updatedApplication == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<JobApplication> deleteApplication(@PathVariable int id) {
        JobApplication deleteJobApplication = service.deleteApplication(id);
        if (deleteJobApplication == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deleteJobApplication);
    }
}
