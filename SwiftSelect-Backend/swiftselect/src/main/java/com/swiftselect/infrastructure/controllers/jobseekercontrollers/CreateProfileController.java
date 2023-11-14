package com.swiftselect.infrastructure.controllers.jobseekercontrollers;

import com.swiftselect.payload.request.jsrequests.jsprofilerequests.*;
import com.swiftselect.services.JobSeekerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-seeker/create-profile")
public class CreateProfileController {
    private final JobSeekerService jobSeekerService;


    @PostMapping("/work-experience")
    public ResponseEntity<String> newWorkExperience(@RequestBody JSWorkExperienceRequest workExperience) {
        return jobSeekerService.newWorkExperience(workExperience);
    }

    @PostMapping("/education")
    public ResponseEntity<String> newEducation(@RequestBody EducationRequest educationRequest) {
        return jobSeekerService.newEducation(educationRequest);
    }

    @PostMapping("/skill")
    public ResponseEntity<String> newSkills(@RequestBody SkillsRequest skillsRequest) {
        return jobSeekerService.newSkills(skillsRequest);
    }

    @PostMapping("/license")
    public ResponseEntity<String> newLicense(@RequestBody LicenseRequest licenseRequest) {
        return jobSeekerService.newLicense(licenseRequest);
    }

    @PostMapping("/certification")
    public ResponseEntity<String> newCertification(@RequestBody CertificationRequest certificationRequest) {
        return jobSeekerService.newCertification(certificationRequest);
    }

    @PostMapping("/language")
    public ResponseEntity<String> newLanguage(@RequestBody LanguageRequest languageRequest) {
        return jobSeekerService.newLanguage(languageRequest);
    }

    @PostMapping("/job-preference")
    public ResponseEntity<String> newJobPreference(@RequestBody JobPreferenceRequest preferenceRequest) {
        return jobSeekerService.newJobPreference(preferenceRequest);
    }
}
