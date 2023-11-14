package com.swiftselect.infrastructure.controllers.jobseekercontrollers;

import com.swiftselect.payload.request.authrequests.ResetPasswordRequest;
import com.swiftselect.services.JobSeekerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-seeker")
public class JobSeekerController {
    private final JobSeekerService jobSeekerService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(final HttpServletRequest request, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return jobSeekerService.resetPassword(request, resetPasswordRequest);
    }
}
