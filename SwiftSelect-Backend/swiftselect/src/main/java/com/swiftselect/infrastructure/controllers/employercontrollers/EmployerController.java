package com.swiftselect.infrastructure.controllers.employercontrollers;

import com.swiftselect.payload.request.employerreqests.EmployerUpdateProfileRequest;
import com.swiftselect.payload.request.authrequests.ResetPasswordRequest;
import com.swiftselect.services.EmployerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employer")
public class EmployerController {
    private final EmployerService employerService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(final HttpServletRequest request, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return employerService.resetPassword(request, resetPasswordRequest);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<String> updateProfile(@RequestBody EmployerUpdateProfileRequest profileRequest) {
        return employerService.updateProfile(profileRequest);
    }

    @DeleteMapping("/delete-job-post/{post-id}")
    public ResponseEntity<String> deleteJobPost(@AuthenticationPrincipal UserDetails userDetails,
                                                @PathVariable("post-id") Long postId) {
        return employerService.deleteJobPost(userDetails.getUsername(), postId);
    }
}
