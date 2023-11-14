package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.profile.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Certification findByIdAndJobSeeker_Id(Long id, Long jobSeekerId);
}