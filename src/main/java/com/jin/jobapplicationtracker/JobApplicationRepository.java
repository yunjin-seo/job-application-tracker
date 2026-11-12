package com.jin.jobapplicationtracker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository 
        extends JpaRepository<JobApplication, Integer> {
    
         List<JobApplication> findByStatus(ApplicationStatus status);
        
         List<JobApplication> findByCompanyContainingIgnoreCase(String company);

         List<JobApplication> findByJobTitleContainingIgnoreCase(String jobTitle);
}
