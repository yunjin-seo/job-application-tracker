package com.jin.jobapplicationtracker;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;
    
    
    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }
    

    // Service
    public List<JobApplication> getApplications() {
        return repository.findAll();
    }

    public JobApplication getApplication(int id) {
        return repository.findById(id).orElse(null);
    }


    public JobApplication addApplication(JobApplication application) {
        return repository.save(application);
        //application 객체를 database에 저장해라
    }

    public JobApplication updateApplication(int id, JobApplication newApplication) {
        if (!repository.existsById(id)) {
            return null;
        }

        newApplication.setId(id);
        return repository.save(newApplication);
    }

    public JobApplication deleteApplication(int id) {
        
        JobApplication application =repository.findById(id).orElse(null);

        if (application == null) {
            return null;
        }

        repository.deleteById(id);
        return application;
    }
}
