package com.jin.jobapplicationtracker;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JobApplicationServiceTest {

    @Test
    void getApplicationReturnsResponse() {
        JobApplicationRepository repository =
                mock(JobApplicationRepository.class);

        JobApplicationService service =
                new JobApplicationService(repository);


        JobApplication application = new JobApplication(
            "Google",
            "Backend Engineer",
            ApplicationStatus.APPLIED
        );
        application.setId(7);
        
        when(repository.findById(7))
            .thenReturn(Optional.of(application));

        JobApplicationResponse response = service.getApplication(7);
        assertThat(response.company()).isEqualTo("Google");
        assertThat(response.id()).isEqualTo(7);
        assertThat(response.jobTitle()).isEqualTo("Backend Engineer");
        assertThat(response.status()).isEqualTo(ApplicationStatus.APPLIED);

    }
    @Test
    void getApplicationThrowsWhenNotFound() {
        
        JobApplicationRepository repository =
                mock(JobApplicationRepository.class);

        JobApplicationService service =
                new JobApplicationService(repository);


        
        when(repository.findById(999))
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getApplication(999))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Job application not found");
    }
    @Test
    void createApplicationReturnsSavedApplication() {
       JobApplicationRepository repository = mock(JobApplicationRepository.class);
       JobApplicationService service = new JobApplicationService(repository);
        
       JobApplicationRequest request = new JobApplicationRequest(
            "Google",
            "Backend Engineer",
            ApplicationStatus.APPLIED
        );
       JobApplication savedApplication = new JobApplication(
        "Google",
        "Backend Engineer",
        ApplicationStatus.APPLIED
        );
        savedApplication.setId(7);
        when(repository.save(any(JobApplication.class)))
            .thenReturn(savedApplication);

        JobApplicationResponse response = service.createApplication(request);

        assertThat(response.id()).isEqualTo(7);
        assertThat(response.company()).isEqualTo("Google");
        assertThat(response.jobTitle()).isEqualTo("Backend Engineer");
        assertThat(response.status()).isEqualTo(ApplicationStatus.APPLIED);

    }
    @Test
    void updateApplicationChangesFields() {
        JobApplicationRepository repository = mock(JobApplicationRepository.class);

        JobApplicationService service = new JobApplicationService(repository);
    

        JobApplication application = new JobApplication(
            "Google",
            "Backend Engineer",
            ApplicationStatus.APPLIED
        );

        application.setId(7);
        JobApplicationRequest request = new JobApplicationRequest(
            "Google", 
            "Senior Backend Engineer", 
            ApplicationStatus.INTERVIEW);
        
        when(repository.findById(7))
            .thenReturn(Optional.of(application));

        JobApplicationResponse response = service.updateApplication(7, request);
        assertThat(response.jobTitle()).isEqualTo("Senior Backend Engineer");
        assertThat(response.status()).isEqualTo(ApplicationStatus.INTERVIEW);
    }
    
    @Test
    void deleteApplication(){
        JobApplicationRepository repository = mock(JobApplicationRepository.class);
        JobApplicationService service = new JobApplicationService(repository);

        JobApplication application = new JobApplication(
            "Google",
            "Backend Engineer",
            ApplicationStatus.APPLIED
        );

        application.setId(7);

        JobApplicationRequest request = new JobApplicationRequest(
            "Google", 
            "Senior Backend Engineer", 
            ApplicationStatus.INTERVIEW);
            
        when(repository.findById(7))
            .thenReturn(Optional.of(application));
        
        service.deleteApplication(7);

        verify(repository).delete(application);
    }
}
