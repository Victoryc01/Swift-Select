package com.swiftselect.repositories;

import com.swiftselect.domain.entities.jobpost.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}
