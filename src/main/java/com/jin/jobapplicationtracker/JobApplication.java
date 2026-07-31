package com.jin.jobapplicationtracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class JobApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //만들어진 값 방식 id 생성을 방식을 모아 놓은 종류  자동 증가
    private int id;
    
    @NotBlank
    private String company;
    
    @NotBlank
    private String jobTitle;
    
    @NotBlank
    private String status;

    public JobApplication() {
    
    }
    public JobApplication(String company, String jobTitle, String status){
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

    public String getStatus(){
        return status;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
