package com.jin.jobapplicationtracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class JobApplicationController {
    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<JobApplicationResponse>
        getApplication(@PathVariable int id) {
        JobApplicationResponse response = service.getApplication(id);
        return ResponseEntity 
            .ok(response);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<JobApplicationResponse>> getApplications(@RequestParam (required = false) ApplicationStatus status) {
        List<JobApplicationResponse> responses;

        if(status == null){
            responses = service.getApplications();
        }else{
            responses = service.getApplicationsByStatus(status);

        }
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/applications")
    public ResponseEntity<JobApplicationResponse> createApplication(@Valid @RequestBody JobApplicationRequest request) {
    
        JobApplicationResponse response = service.createApplication(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<JobApplicationResponse> updateApplication(@PathVariable int id,
            @Valid @RequestBody JobApplicationRequest request) {
            JobApplicationResponse response = service.updateApplication(id, request);

            return ResponseEntity.ok(response); 
    
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable int id) {
       service.deleteApplication(id);
        
       //204
       return ResponseEntity
            .noContent()
            .build();
    }
    @GetMapping("/applications/search")
    public ResponseEntity<List<JobApplicationResponse>> searchApplicationsByCompany(@RequestParam String company){
        List<JobApplicationResponse> responses = service.searchApplicationsByCompany(company);
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/applications/search/job-title")
    public ResponseEntity<List<JobApplicationResponse>> searchApplicationsByJobTitle(@RequestParam String jobTitle){
        List<JobApplicationResponse> responses = service.searchApplicationsByJobTitle(jobTitle);
        return ResponseEntity.ok(responses);
    }
}
