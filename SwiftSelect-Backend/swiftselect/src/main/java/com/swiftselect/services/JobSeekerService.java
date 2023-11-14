package com.swiftselect.services;

import com.swiftselect.payload.request.authrequests.ResetPasswordRequest;
import com.swiftselect.payload.request.jsrequests.jsprofilerequests.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface JobSeekerService {
     ResponseEntity<String> resetPassword(HttpServletRequest request, ResetPasswordRequest resetPasswordRequest);

     // UPDATE PROFILE

     ResponseEntity<String> contactInfoUpdate(JSContactInfoRequest contactInfoRequest);

     ResponseEntity<String> locationInfoUpdate(JSLocationInfoRequest locationInfoRequest);

     ResponseEntity<String> resumeUpdate(JSResumeRequests resumeRequests);

     ResponseEntity<String> workExperienceUpdate(JSWorkExperienceRequest workExperience, long id);

     ResponseEntity<String> educationUpdate(EducationRequest educationRequest, long id);

     ResponseEntity<String> skillsUpdate(SkillsRequest skillsRequest, long id);

     ResponseEntity<String> licenseUpdate(LicenseRequest licenseRequest, long id);

     ResponseEntity<String> certificationUpdate(CertificationRequest certificationRequest, long id);

     ResponseEntity<String> languageUpdate(LanguageRequest languageRequest, long id);

     ResponseEntity<String> jobPreferenceUpdate(JobPreferenceRequest preferenceRequest, long id);

     ResponseEntity<String> jobExpectationUpdate(JobExpectationsRequest jobExpectationsRequest);

     ResponseEntity<String> socialsUpdate(JSSocialsRequests socialsRequests);


     // CREATING NEW PROFILE

     ResponseEntity<String> newWorkExperience(JSWorkExperienceRequest workExperience);

     ResponseEntity<String> newEducation(EducationRequest educationRequest);

     ResponseEntity<String> newSkills(SkillsRequest skillsRequest);

     ResponseEntity<String> newLicense(LicenseRequest licenseRequest);

     ResponseEntity<String> newCertification(CertificationRequest certificationRequest);

     ResponseEntity<String> newLanguage(LanguageRequest languageRequest);

     ResponseEntity<String> newJobPreference(JobPreferenceRequest preferenceRequest);

}
