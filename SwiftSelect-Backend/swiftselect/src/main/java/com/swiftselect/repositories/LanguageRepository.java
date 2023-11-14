package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobseeker.profile.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByIdAndJobSeeker_Id(Long id, Long jobSeekerId);
}