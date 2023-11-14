package com.swiftselect.repositories;

import com.swiftselect.domain.entities.employer.EmployerVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerVerificationTokenRepository extends JpaRepository<EmployerVerificationToken, Long> {
    Optional<EmployerVerificationToken> findByEmployer_Email(String email);
    Optional<EmployerVerificationToken> findByToken(String token);
}
