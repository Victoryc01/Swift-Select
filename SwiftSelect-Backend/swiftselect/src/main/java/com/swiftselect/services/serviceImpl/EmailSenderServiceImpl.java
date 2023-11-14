package com.swiftselect.services.serviceImpl;

import com.swiftselect.domain.entities.employer.Employer;
import com.swiftselect.domain.entities.jobseeker.JobSeeker;
import com.swiftselect.infrastructure.event.events.ForgotPasswordEvent;
import com.swiftselect.repositories.EmployerRepository;
import com.swiftselect.repositories.JobSeekerRepository;
import com.swiftselect.services.EmailSenderService;
import com.swiftselect.payload.request.MailRequest;
import com.swiftselect.utils.HelperClass;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;
    private final JobSeekerRepository jobSeekerRepository;
    private final EmployerRepository employerRepository;
    private final HelperClass helperClass;

    private JobSeeker jobSeeker;
    private Employer employer;

    private Optional<JobSeeker> jobSeekerOptional;
    private Optional<Employer> employerOptional;


    @Value("${spring.mail.username}")
    private String sendMail;

    @Override
    public void sendEmailAlert(MailRequest mailDTO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(sendMail);
            simpleMailMessage.setTo(mailDTO.getTo());
            simpleMailMessage.setSubject(mailDTO.getSubject());
            simpleMailMessage.setText(mailDTO.getMessage());

            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotificationEmail(String url,
                                      String email,
                                      String firstName,
                                      String subject,
                                      String description) {

        String action = "Contact Us";
        String serviceProvider = "Swift Select Customer Service";

        helperClass.sendNotificationEmail(
                firstName,
                url,
                mailSender,
                sendMail,
                email,
                action,
                serviceProvider,
                subject,
                description
        );
    }

    @Override
    public void sendRegistrationEmailVerification(String url, String email, String firstName) {
        String action = "Verify Email";
        String serviceProvider = "Swift Select Registration Portal Service";
        String subject = "Email Verification";
        String description = "Please follow the link below to complete your registration.";

        helperClass.sendEmail(
                firstName,
                url,
                mailSender,
                sendMail,
                email,
                action,
                serviceProvider,
                subject,
                description
            );
    }

    @Override
    public void sendForgotPasswordEmailVerification(String url, ForgotPasswordEvent event) {
        jobSeekerOptional = jobSeekerRepository.findByEmail(event.getEmail());
        employerOptional = employerRepository.findByEmail(event.getEmail());

        String action = "Change Password";
        String serviceProvider = "Swift Select Customer Portal Service";
        String subject = "Email Verification";
        String description = "Please follow the link below to change your password.";

        if (jobSeekerOptional.isPresent()) {
            jobSeeker = jobSeekerOptional.get();


            helperClass.sendEmail(
                    jobSeeker.getFirstName(),
                    url,
                    mailSender,
                    sendMail,
                    event.getEmail(),
                    action,
                    serviceProvider,
                    subject,
                    description
            );
        } else {
            employer = employerOptional.get();

            helperClass.sendEmail(
                    employer.getFirstName(),
                    url,
                    mailSender,
                    sendMail,
                    event.getEmail(),
                    action,
                    serviceProvider,
                    subject,
                    description
            );
        }
    }
}
