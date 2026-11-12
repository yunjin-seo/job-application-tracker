package com.jin.jobapplicationtracker;


import java.util.List;

import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;
    
    
    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }
    
   

    // Service
    public JobApplicationResponse getApplication(int id) {
        JobApplication application =repository.findById(id)
            .orElseThrow(
                ()-> new IllegalArgumentException("Job application not found")
            );
        
        return new JobApplicationResponse(
            application.getId(),
            application.getCompany(),
            application.getJobTitle(),
            application.getStatus()
        );
    }
    
    public List<JobApplicationResponse> getApplications() {

    List<JobApplication> applications =
            repository.findAll();

    return applications.stream()
            .map(jobApplication -> new JobApplicationResponse(
                jobApplication.getId(),
                jobApplication.getCompany(),
                jobApplication.getJobTitle(),
                jobApplication.getStatus()
                )
            )
            .toList();
    }
    


    public JobApplicationResponse createApplication(
        JobApplicationRequest request) {

        JobApplication jobApplication = new JobApplication(
            request.company(),
            request.jobTitle(),
            request.status()
        );

        JobApplication savedJobApplication =repository.save(jobApplication);
        return new JobApplicationResponse(
                savedJobApplication.getId(),
                savedJobApplication.getCompany(),
                savedJobApplication.getJobTitle(),
                savedJobApplication.getStatus()
        );
    }
    @Transactional
    public JobApplicationResponse updateApplication(int id, JobApplicationRequest request) {
        JobApplication application =repository.findById(id)
                                        .orElseThrow(
                                        ()-> new IllegalArgumentException("Job application not found.")
                                     );
        application.update(
                                request.company(),
                                request.jobTitle(),
                                request.status());
        return new JobApplicationResponse(
                application.getId(),
                application.getCompany(),
                application.getJobTitle(),
                application.getStatus()
        );
    }
    @Transactional 
    public void deleteApplication(int id) {
        JobApplication application = repository.findById(id)
                .orElseThrow(
                    ()-> new IllegalArgumentException("Job application not found.")
                );

        repository.delete(application);

    }
    public List<JobApplicationResponse> getApplicationsByStatus(ApplicationStatus status){
        List<JobApplication> applications =repository.findByStatus(status);
        return applications.stream()
                .map(jobApplication -> new JobApplicationResponse(
                    jobApplication.getId(), 
                    jobApplication.getCompany(),
                    jobApplication.getJobTitle(),
                    jobApplication.getStatus())
                )
                .toList();
                
    }
    public List<JobApplicationResponse> searchApplicationsByCompany(String company){
        List<JobApplication> applications = repository.findByCompanyContainingIgnoreCase(company);
        
        return applications.stream()
                            .map(application -> new JobApplicationResponse(
                                application.getId(),
                                application.getCompany(), 
                                application.getJobTitle(),
                                application.getStatus()
                            ))
                            .toList();
    }
    public List<JobApplicationResponse> searchApplicationsByJobTitle(String jobTitle){
        List<JobApplication> applications = repository.findByJobTitleContainingIgnoreCase(jobTitle);
        
        return applications.stream()
                            .map(application -> new JobApplicationResponse(
                                application.getId(),
                                application.getCompany(), 
                                application.getJobTitle(),
                                application.getStatus()
                            ))
                            .toList();
    }
}
