package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.JobSeekerVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerVerificationTokenRepository extends JpaRepository<JobSeekerVerificationToken, Long> {
    Optional<JobSeekerVerificationToken> findByJobSeeker_Email(String email);
    Optional<JobSeekerVerificationToken> findByToken(String token);
}
