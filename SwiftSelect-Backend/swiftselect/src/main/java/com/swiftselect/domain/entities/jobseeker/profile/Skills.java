package com.swiftselect.domain.entities.jobseeker.profile;

import com.swiftselect.domain.entities.jobseeker.JobSeeker;
import com.swiftselect.domain.entities.base.Base;
import com.swiftselect.domain.enums.YearsOfExp;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "skills")
public class Skills extends Base {
    private String skill;

    @Enumerated(EnumType.STRING)
    private YearsOfExp yearsOfExperience;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;
}
