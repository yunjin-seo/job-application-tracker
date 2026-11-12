package com.jin.jobapplicationtracker;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobApplicationRequest(

        @NotBlank(message = "Company is required") 
        String company,

        @NotBlank (message = "Job title is required")
        String jobTitle,

        @NotNull (message = "Status is required")
        ApplicationStatus status) {

}
