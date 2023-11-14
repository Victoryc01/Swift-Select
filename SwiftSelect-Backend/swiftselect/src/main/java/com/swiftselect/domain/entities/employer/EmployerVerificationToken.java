package com.swiftselect.domain.entities.employer;

import com.swiftselect.domain.entities.employer.Employer;
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
@Table(name = "employer_verification_token")
public class EmployerVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    private static final int EXPIRATION_TIME = 15;

    @OneToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    public EmployerVerificationToken(String token, Employer employer) {
        this.token = token;
        this.employer = employer;
        this.expirationTime = this.getExpirationTime();
    }

    public EmployerVerificationToken(String token) {
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
