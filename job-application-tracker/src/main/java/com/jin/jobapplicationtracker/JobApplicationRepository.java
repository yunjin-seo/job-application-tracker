package com.jin.jobapplicationtracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository 
        extends JpaRepository<JobApplication, Integer> {
    
}
