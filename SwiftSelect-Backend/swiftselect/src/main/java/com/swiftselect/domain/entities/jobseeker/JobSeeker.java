package com.swiftselect.domain.entities.jobseeker;

import com.swiftselect.domain.entities.jobpost.Applications;
import com.swiftselect.domain.entities.Report;
import com.swiftselect.domain.entities.base.Person;
import com.swiftselect.domain.entities.jobseeker.profile.*;
import com.swiftselect.domain.enums.Gender;
import com.swiftselect.domain.enums.JobType;
import com.swiftselect.domain.enums.PayRate;
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
@Table(name = "job_seeker")
public class JobSeeker extends Person {
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String dateOfBirth;

    private String resume;

    private String coverLetter;

    private String workSchedule;

    private String basePay;

    @Enumerated(EnumType.STRING)
    private PayRate payRate;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<WorkExperience> workExperiences = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<Education> educations = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<Skills> skills = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<License> licenses = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<Certification> certifications = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<Language> languages = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<JobPreference> jobPreferences = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<Applications> applications = new HashSet<>();

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private Set<Report> report = new HashSet<>();

    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private JobSeekerVerificationToken jobSeekerVerificationToken;
}