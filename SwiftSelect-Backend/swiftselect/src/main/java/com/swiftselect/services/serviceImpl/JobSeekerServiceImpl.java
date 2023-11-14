package com.swiftselect.services.serviceImpl;

import com.swiftselect.domain.entities.jobseeker.JobSeeker;
import com.swiftselect.domain.entities.jobseeker.profile.*;
import com.swiftselect.infrastructure.event.eventpublisher.EventPublisher;
import com.swiftselect.infrastructure.security.JwtAuthenticationFilter;
import com.swiftselect.payload.request.jsrequests.jsprofilerequests.*;
import com.swiftselect.repositories.*;
import com.swiftselect.services.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.swiftselect.infrastructure.exceptions.ApplicationException;
import com.swiftselect.infrastructure.security.JwtTokenProvider;
import com.swiftselect.payload.request.authrequests.ResetPasswordRequest;
import com.swiftselect.utils.HelperClass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobSeekerServiceImpl implements JobSeekerService {
    private final EducationRepository educationRepository;
    private final JobPreferenceRepository jobPreferenceRepository;
    private final LanguageRepository languageRepository;
    private final LicenseRepository licenseRepository;
    private final SkillsRepository skillsRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final AuthenticationManager authenticationManager;
    private final JobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final HelperClass helperClass;
    private final EventPublisher eventPublisher;
    private final ModelMapper modelMapper;
    private final HttpServletRequest request;
    private final CertificationRepository certificationRepository;


    private JobSeeker getJobSeeker() {
        String token = helperClass.getTokenFromHttpRequest(request);

        String email = jwtTokenProvider.getUserName(token);

        return  jobSeekerRepository
                .findByEmail(email)
                .orElseThrow(() -> new ApplicationException("User does not exist with email " + email, HttpStatus.NOT_FOUND));
    }


    @Override
    public ResponseEntity<String> resetPassword(HttpServletRequest request, ResetPasswordRequest resetPasswordRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        String email = jobSeeker.getEmail();

        // Authentication manager to authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        resetPasswordRequest.getOldPassword()
                )
        );

        jobSeeker.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));

        jobSeekerRepository.save(jobSeeker);

        eventPublisher.notificationMailEventPublisher(email,
                jobSeeker.getFirstName(), "Password Reset",
                "Password successfully changed. If you did not initiate this, please send a reply to this email",
                request);

        return ResponseEntity.ok("Password successfully changed");
    }

    @Override
    public ResponseEntity<String> contactInfoUpdate(JSContactInfoRequest contactInfoRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        jobSeeker.setFirstName(contactInfoRequest.getFirstName());
        jobSeeker.setLastName(contactInfoRequest.getLastName());
        jobSeeker.setPhoneNumber(contactInfoRequest.getPhoneNumber());

        jobSeekerRepository.save(jobSeeker);

        return ResponseEntity.ok("update successful");
    }

    @Override
    public ResponseEntity<String> locationInfoUpdate(JSLocationInfoRequest locationInfoRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        jobSeeker.setCountry(locationInfoRequest.getCountry());
        jobSeeker.setState(locationInfoRequest.getState());
        jobSeeker.setCity(locationInfoRequest.getCity());
        jobSeeker.setAddress(locationInfoRequest.getAddress());
        jobSeeker.setPostalCode(locationInfoRequest.getPostalCode());

        jobSeekerRepository.save(jobSeeker);

        return ResponseEntity.ok("update successful");
    }

    @Override
    public ResponseEntity<String> resumeUpdate(JSResumeRequests resumeRequests) {
        JobSeeker jobSeeker = getJobSeeker();

        jobSeeker.setResume(resumeRequests.getResume());
        jobSeeker.setCoverLetter(resumeRequests.getCoverLetter());

        jobSeekerRepository.save(jobSeeker);

        return ResponseEntity.ok("update successful");
    }

    @Override
    public ResponseEntity<String> workExperienceUpdate(JSWorkExperienceRequest workExperienceRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        WorkExperience workExperience = workExperienceRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (workExperience != null) {
            workExperience.setJobTitle(workExperienceRequest.getJobTitle());
            workExperience.setCompanyName(workExperienceRequest.getCompanyName());
            workExperience.setStartDate(workExperienceRequest.getStartDate());
            workExperience.setStopDate(workExperienceRequest.getStopDate());

            workExperienceRepository.save(workExperience);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> educationUpdate(EducationRequest educationRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        Education education = educationRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (education != null) {
            education.setEducationLevel(educationRequest.getEducationLevel());
            education.setFieldOfStudy(educationRequest.getFieldOfStudy());
            education.setYearOfGraduation(educationRequest.getYearOfGraduation());

            educationRepository.save(education);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> skillsUpdate(SkillsRequest skillsRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        Skills skills = skillsRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (skills != null) {
            skills.setSkill(skillsRequest.getSkill());
            skills.setYearsOfExperience(skillsRequest.getYearsOfExperience());

            skillsRepository.save(skills);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> licenseUpdate(LicenseRequest licenseRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        License license = licenseRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (license != null) {
            license.setLicenseName(licenseRequest.getLicenseName());
            license.setExpirationDate(licenseRequest.getExpirationDate());

            licenseRepository.save(license);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> certificationUpdate(CertificationRequest certificationRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        Certification certification = certificationRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (certification != null) {
            certification.setName(certificationRequest.getName());
            certification.setExpiration(certificationRequest.getExpiration());

            certificationRepository.save(certification);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> languageUpdate(LanguageRequest languageRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        Language language = languageRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (language != null) {
            language.setLanguage(languageRequest.getLanguage());
            language.setProficiency(languageRequest.getProficiency());

            languageRepository.save(language);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> jobPreferenceUpdate(JobPreferenceRequest preferenceRequest, long id) {
        JobSeeker jobSeeker = getJobSeeker();

        JobPreference jobPreference = jobPreferenceRepository.findByIdAndJobSeeker_Id(id, jobSeeker.getId());

        if (jobPreference != null) {
            jobPreference.setIndustry(preferenceRequest.getIndustry());

            jobPreferenceRepository.save(jobPreference);

            return ResponseEntity.ok("update successful");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not found");
    }

    @Override
    public ResponseEntity<String> jobExpectationUpdate(JobExpectationsRequest jobExpectationsRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        jobSeeker.setWorkSchedule(jobExpectationsRequest.getWorkSchedule());
        jobSeeker.setBasePay(jobExpectationsRequest.getBasePay());
        jobSeeker.setPayRate(jobExpectationsRequest.getPayRate());
        jobSeeker.setJobType(jobExpectationsRequest.getJobType());

        jobSeekerRepository.save(jobSeeker);

        return ResponseEntity.ok("update successful");
    }

    @Override
    public ResponseEntity<String> socialsUpdate(JSSocialsRequests socialsRequests) {
        JobSeeker jobSeeker = getJobSeeker();

        jobSeeker.setFacebook(socialsRequests.getFacebook());
        jobSeeker.setTwitter(socialsRequests.getTwitter());
        jobSeeker.setInstagram(socialsRequests.getInstagram());

        jobSeekerRepository.save(jobSeeker);

        return ResponseEntity.ok("update successful");
    }

    @Override
    public ResponseEntity<String> newWorkExperience(JSWorkExperienceRequest workExperienceRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        WorkExperience workExperience = modelMapper.map(workExperienceRequest, WorkExperience.class);
        workExperience.setJobSeeker(jobSeeker);

        workExperienceRepository.save(workExperience);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @Override
    public ResponseEntity<String> newEducation(EducationRequest educationRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        Education education = modelMapper.map(educationRequest, Education.class);
        education.setJobSeeker(jobSeeker);

        educationRepository.save(education);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @Override
    public ResponseEntity<String> newSkills(SkillsRequest skillsRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        Skills skills = modelMapper.map(skillsRequest, Skills.class);
        skills.setJobSeeker(jobSeeker);

        skillsRepository.save(skills);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @Override
    public ResponseEntity<String> newLicense(LicenseRequest licenseRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        License license = modelMapper.map(licenseRequest, License.class);
        license.setJobSeeker(jobSeeker);

        licenseRepository.save(license);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @Override
    public ResponseEntity<String> newCertification(CertificationRequest certificationRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        Certification certification = modelMapper.map(certificationRequest, Certification.class);
        certification.setJobSeeker(jobSeeker);

        certificationRepository.save(certification);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @Override
    public ResponseEntity<String> newLanguage(LanguageRequest languageRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        Language language = modelMapper.map(languageRequest,  Language.class);
        language.setJobSeeker(jobSeeker);

        languageRepository.save(language);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }

    @Override
    public ResponseEntity<String> newJobPreference(JobPreferenceRequest preferenceRequest) {
        JobSeeker jobSeeker = getJobSeeker();

        JobPreference jobPreference = modelMapper.map(preferenceRequest, JobPreference.class);
        jobPreference.setJobSeeker(jobSeeker);

        jobPreferenceRepository.save(jobPreference);

        return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
    }
}
