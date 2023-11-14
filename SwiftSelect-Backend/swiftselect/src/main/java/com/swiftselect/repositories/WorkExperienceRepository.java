package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.profile.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    WorkExperience findByIdAndJobSeeker_Id(Long id, Long jobSeekerId);
}