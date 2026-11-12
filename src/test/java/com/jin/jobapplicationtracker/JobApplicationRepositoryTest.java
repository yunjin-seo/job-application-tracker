package com.jin.jobapplicationtracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository repository;
    
    @Test
    void findByStatus_returnsOnlyMatchingApplications() {

    JobApplication google = new JobApplication(
        "Google",
        "Backend Engineer",
        ApplicationStatus.APPLIED
    );

    JobApplication amazon = new JobApplication(
        "Amazon",
        "Java Developer",
        ApplicationStatus.INTERVIEW
    );
    JobApplication microsoft = new JobApplication(
        "Microsoft",
        "software Engineer",
        ApplicationStatus.INTERVIEW
    );
    repository.save(google);
    repository.save(amazon);
    repository.save(microsoft);
    // Act: 테스트할 method 실행
    List<JobApplication> results =
            repository.findByStatus(
                ApplicationStatus.INTERVIEW
        );
    // Assert: 실제 결과가 예상 결과와 같은지 검사
    assertThat(results).hasSize(2);
   
    assertThat(results)
        .extracting(application -> application.getCompany())
        .containsExactlyInAnyOrder(
                "Amazon",
                "Microsoft"
        );
    }
    @Test
    void findByCompanyContainingIgnoreCase(){
        JobApplication google = new JobApplication("Google","Backend Engineer",ApplicationStatus.APPLIED);
        JobApplication amazon = new JobApplication("Amazon","Java Developer",ApplicationStatus.INTERVIEW);
        
        repository.save(google);
        repository.save(amazon);

        List<JobApplication> result =repository.findByCompanyContainingIgnoreCase("GOO");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCompany())
                    .isEqualTo("Google");
    }
    @Test
    void findByJobTitleContainingIgnoreCase(){
        JobApplication netflix = new JobApplication("Netflix","Backend Developer",ApplicationStatus.APPLIED);
        JobApplication google = new JobApplication("Google","Frontend Developer",ApplicationStatus.INTERVIEW);
        
        repository.save(netflix);
        repository.save(google);

        List<JobApplication> result = repository.findByJobTitleContainingIgnoreCase("BACK");
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getJobTitle()).isEqualTo("Backend Developer");
    }

}