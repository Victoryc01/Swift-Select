package com.swiftselect.infrastructure.controllers.jobseekercontrollers;

import com.swiftselect.payload.request.jsrequests.jsprofilerequests.*;
import com.swiftselect.services.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-seeker/update-profile")
public class UpdateProfileController {
    private final JobSeekerService jobSeekerService;

    // PERSONAL INFORMATION

    @PutMapping("/contact-info")
    public ResponseEntity<String> contactInfoUpdate(@RequestBody JSContactInfoRequest contactInfoRequest) {

        return jobSeekerService.contactInfoUpdate(contactInfoRequest);
    }

    @PutMapping("/location-info")
    public ResponseEntity<String> locationInfoUpdate(@RequestBody JSLocationInfoRequest locationInfoRequest) {

        return jobSeekerService.locationInfoUpdate(locationInfoRequest);
    }

    @PutMapping("/resume")
    public ResponseEntity<String> resumeUpdate(@RequestBody JSResumeRequests resumeRequests) {

        return jobSeekerService.resumeUpdate(resumeRequests);
    }

    @PutMapping("/socials")
    public ResponseEntity<String> socialsUpdate(@RequestBody JSSocialsRequests socialsRequests) {

        return jobSeekerService.socialsUpdate(socialsRequests);
    }

    // QUALIFICATIONS

    @PutMapping("/work-experience/{id}")
    public ResponseEntity<String> workExperienceUpdate(@RequestBody JSWorkExperienceRequest workExperience,
                                                       @PathVariable("id") long id) {

        return jobSeekerService.workExperienceUpdate(workExperience, id);
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<String> educationUpdate(@RequestBody EducationRequest educationRequest,
                                                  @PathVariable("id") long id) {

        return jobSeekerService.educationUpdate(educationRequest, id);
    }

    @PutMapping("/skill/{id}")
    public ResponseEntity<String> skillsUpdate(@RequestBody SkillsRequest skillsRequest,
                                               @PathVariable("id") long id) {

        return jobSeekerService.skillsUpdate(skillsRequest, id);
    }

    @PutMapping("/license/{id}")
    public ResponseEntity<String> licenseUpdate(@RequestBody LicenseRequest licenseRequest,
                                                @PathVariable("id") long id) {

        return jobSeekerService.licenseUpdate(licenseRequest, id);
    }

    @PutMapping("/certification/{id}")
    public ResponseEntity<String> certificationUpdate(@RequestBody CertificationRequest certificationRequest,
                                                      @PathVariable("id") long id) {

        return jobSeekerService.certificationUpdate(certificationRequest, id);
    }

    @PutMapping("/language/{id}")
    public ResponseEntity<String> languageUpdate(@RequestBody LanguageRequest languageRequest,
                                                 @PathVariable("id") long id) {

        return jobSeekerService.languageUpdate(languageRequest, id);
    }

    // JOB PREFERENCES

    @PutMapping("/job-preference/{id}")
    public ResponseEntity<String> jobPreferenceUpdate(@RequestBody JobPreferenceRequest preferenceRequest,
                                                      @PathVariable("id") long id) {

        return jobSeekerService.jobPreferenceUpdate(preferenceRequest, id);
    }

    @PutMapping("/job-expectation")
    public ResponseEntity<String> jobExpectationUpdate(@RequestBody JobExpectationsRequest jobExpectationsRequest) {

        return jobSeekerService.jobExpectationUpdate(jobExpectationsRequest);
    }
}
