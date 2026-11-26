package com.jin.jobapplicationtracker;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Enumerated;

@Entity
public class JobApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    private int id;
    
    @NotBlank
    private String company;
    
    @NotBlank
    private String jobTitle;
    
    @Enumerated(EnumType.STRING)

    private ApplicationStatus status;

    public JobApplication() {
    
    }
    public JobApplication(String company, String jobTitle, ApplicationStatus status){
        this.company = company;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    public String getCompany(){
        return company;
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public ApplicationStatus getStatus(){
        return status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void update(String company, String jobTitle, ApplicationStatus status){
        this.company = company;
        this.jobTitle = jobTitle;
        this.status = status;
    }

}
