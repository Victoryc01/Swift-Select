package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.profile.JobPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPreferenceRepository extends JpaRepository<JobPreference, Long> {
    JobPreference findByIdAndJobSeeker_Id(Long id, Long jobSeekerId);
}