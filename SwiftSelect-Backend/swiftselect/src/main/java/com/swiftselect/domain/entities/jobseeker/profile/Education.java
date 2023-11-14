package com.swiftselect.domain.entities.jobseeker.profile;

import com.swiftselect.domain.entities.jobseeker.JobSeeker;
import com.swiftselect.domain.entities.base.Base;
import com.swiftselect.domain.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "education")
public class Education extends Base {
    private EducationLevel educationLevel;

    private String fieldOfStudy;

    private String yearOfGraduation;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;
}
