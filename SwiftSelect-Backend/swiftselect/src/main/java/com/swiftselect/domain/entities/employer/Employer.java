package com.swiftselect.domain.entities.employer;

import com.swiftselect.domain.entities.jobpost.JobPost;
import com.swiftselect.domain.entities.base.Person;
import com.swiftselect.domain.enums.CompanyType;
import com.swiftselect.domain.enums.Industry;
import com.swiftselect.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employer")
public class Employer extends Person {
    private String companyName;

    private String companyDescription;

    private Long numberOfEmployees;

    private String website;

    private String facebook;

    private String twitter;

    private String instagram;

    private String position;

    @Enumerated(value = EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(value = EnumType.STRING)
    private Industry industry;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private Set<JobPost> JobPosts = new HashSet<>();

    @OneToOne(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployerVerificationToken employerVerificationToken;
}
