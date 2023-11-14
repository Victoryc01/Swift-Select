package com.swiftselect.services.serviceImpl;

import com.swiftselect.domain.entities.employer.Employer;
import com.swiftselect.domain.entities.jobpost.JobPost;
import com.swiftselect.infrastructure.event.eventpublisher.EventPublisher;
import com.swiftselect.infrastructure.exceptions.ApplicationException;
import com.swiftselect.infrastructure.security.JwtTokenProvider;
import com.swiftselect.payload.request.employerreqests.EmployerUpdateProfileRequest;
import com.swiftselect.payload.request.authrequests.ResetPasswordRequest;
import com.swiftselect.repositories.EmployerRepository;
import com.swiftselect.repositories.JobPostRepository;
import com.swiftselect.services.EmployerService;
import com.swiftselect.utils.HelperClass;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {
    private final AuthenticationManager authenticationManager;
    private final EmployerRepository employerRepository;
    private final HelperClass helperClass;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisher eventPublisher;
    private final HttpServletRequest httpRequest;
    private final JobPostRepository jobPostRepository;


    @Override
    public ResponseEntity<String> resetPassword(HttpServletRequest request, ResetPasswordRequest resetPasswordRequest) {
        String token = helperClass.getTokenFromHttpRequest(request);

        String email = jwtTokenProvider.getUserName(token);

        // Authentication manager to authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        resetPasswordRequest.getOldPassword()
                )
        );

        Employer employer = employerRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new ApplicationException("User does not exist with email " + email, HttpStatus.NOT_FOUND));

        employer.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));

        employerRepository.save(employer);

        eventPublisher.notificationMailEventPublisher(email,
                employer.getFirstName(), "Password Reset",
                "Password successfully changed. If you did not initiate this, please send a reply to this email",
                request);

        return ResponseEntity.ok("Password successfully changed");
    }

    @Override
    public ResponseEntity<String> updateProfile(EmployerUpdateProfileRequest updateProfileRequest) {
        String token = helperClass.getTokenFromHttpRequest(httpRequest);

        String email = jwtTokenProvider.getUserName(token);

        Employer employer = employerRepository.findByEmail(email).get();

        employer.setCompanyName(updateProfileRequest.getCompanyName());
        employer.setCompanyDescription(updateProfileRequest.getCompanyDescription());
        employer.setAddress(updateProfileRequest.getAddress());
        employer.setCountry(updateProfileRequest.getCountry());
        employer.setState(updateProfileRequest.getState());
        employer.setCity(updateProfileRequest.getCity());
        employer.setIndustry(updateProfileRequest.getIndustry());
        employer.setCompanyType(updateProfileRequest.getCompanyType());
        employer.setNumberOfEmployees(updateProfileRequest.getNumberOfEmployees());
        employer.setWebsite(updateProfileRequest.getWebsite());
        employer.setFacebook(updateProfileRequest.getFacebook());
        employer.setTwitter(updateProfileRequest.getTwitter());
        employer.setInstagram(updateProfileRequest.getInstagram());
        employer.setFirstName(updateProfileRequest.getFirstName());
        employer.setLastName(updateProfileRequest.getLastName());
        employer.setPosition(updateProfileRequest.getPosition());
        employer.setPhoneNumber(updateProfileRequest.getPhoneNumber());
        employer.setPostalCode(updateProfileRequest.getPostalCode());

        Employer savedEmployer = employerRepository.save(employer);

        return ResponseEntity.ok("Update Successful");
    }

    @Override
    public ResponseEntity<String> deleteJobPost(String email, Long postId) {

        Optional<JobPost> jobPostOptional = jobPostRepository.findById(postId);

        Optional<Employer> employerOptional = employerRepository.findByEmail(email);

        // Check if the employer with the specified email exists
        if (employerOptional.isPresent()) {
            // Check if the job post exists
            if (jobPostOptional.isPresent()) {
                // Check if the employer associated with the job post is the same as the logged-in employer
                if (jobPostOptional.get().getEmployer().equals(employerOptional.get())) {
                    // If yes, delete the job post
                    jobPostRepository.delete(jobPostOptional.get());
                    return ResponseEntity.ok("Post successfully deleted");
                }
                // If not, throw an exception indicating that the user is not permitted to delete this post
                throw new ApplicationException("You are not permitted to delete this post", HttpStatus.BAD_REQUEST);
            }
            // If the job post with the specified ID does not exist
            throw new ApplicationException("Job post not found", HttpStatus.NOT_FOUND);
        }
        // If the employer with the specified email does not exist
        throw new ApplicationException("Employer not found", HttpStatus.NOT_FOUND);
    }
}
