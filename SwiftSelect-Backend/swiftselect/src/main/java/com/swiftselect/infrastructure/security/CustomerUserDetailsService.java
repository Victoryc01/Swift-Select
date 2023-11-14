package com.swiftselect.infrastructure.security;

import com.swiftselect.domain.entities.employer.Employer;
import com.swiftselect.domain.entities.jobseeker.JobSeeker;
import com.swiftselect.repositories.EmployerRepository;
import com.swiftselect.repositories.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private final EmployerRepository employerRepository;
    private final JobSeekerRepository jobSeekerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (employerRepository.existsByEmail(email)) {
            Employer employer = employerRepository.findByEmail(email).get();

            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(employer.getRole().toString()));

            return new User(
                    employer.getEmail(),
                    employer.getPassword(),
                    authorities
            );
        } else if (jobSeekerRepository.existsByEmail(email)) {
            JobSeeker jobSeeker = jobSeekerRepository.findByEmail(email).get();

            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(jobSeeker.getRole().toString()));

            return new User(
                    jobSeeker.getEmail(),
                    jobSeeker.getPassword(),
                    authorities
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
