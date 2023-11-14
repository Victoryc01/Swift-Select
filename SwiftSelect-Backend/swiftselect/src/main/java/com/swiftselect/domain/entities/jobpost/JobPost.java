package com.swiftselect.domain.entities.jobpost;

import com.swiftselect.domain.entities.Report;
import com.swiftselect.domain.entities.base.Base;
import com.swiftselect.domain.entities.employer.Employer;
import com.swiftselect.domain.enums.Industry;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "job_post")
public class JobPost extends Base {
    private String title;

    private String description;

    private String jobSummary;

    private String location;

    private String employmentType;

    private LocalDateTime applicationDeadline;

    @Enumerated(EnumType.STRING)
    private Industry jobFunction;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    private Set<Applications> applications = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;

    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<>();
}
