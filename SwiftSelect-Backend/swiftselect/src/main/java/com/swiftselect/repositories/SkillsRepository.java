package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.profile.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Skills findByIdAndJobSeeker_Id(Long id, Long jobSeekerId);
}