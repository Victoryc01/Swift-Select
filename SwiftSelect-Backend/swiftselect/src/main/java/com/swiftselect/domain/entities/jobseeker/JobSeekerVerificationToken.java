package com.swiftselect.domain.entities.jobseeker;

import com.swiftselect.domain.entities.jobseeker.JobSeeker;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "job_seeker_verification_token")
public class JobSeekerVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    private static final int EXPIRATION_TIME = 15;

    @OneToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    public JobSeekerVerificationToken(String token, JobSeeker jobSeeker) {
        this.token = token;
        this.jobSeeker = jobSeeker;
        this.expirationTime = this.getExpirationTime();
    }

    public JobSeekerVerificationToken(String token) {
        this.token = token;
        this.expirationTime = this.getExpirationTime();
    }

    public Date getExpirationTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);

        return new Date(calendar.getTime().getTime());
    }
}
