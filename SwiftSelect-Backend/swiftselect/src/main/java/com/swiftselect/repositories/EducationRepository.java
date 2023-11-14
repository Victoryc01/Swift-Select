package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.profile.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
    Education findByIdAndJobSeeker_Id(Long id, Long jobSeekerId);
}