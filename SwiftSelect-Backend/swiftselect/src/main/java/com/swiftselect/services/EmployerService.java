package com.swiftselect.services;

import com.swiftselect.payload.request.employerreqests.EmployerUpdateProfileRequest;
import com.swiftselect.payload.request.authrequests.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface EmployerService {
    ResponseEntity<String> resetPassword(HttpServletRequest request, ResetPasswordRequest resetPasswordRequest);
    ResponseEntity<String> updateProfile(EmployerUpdateProfileRequest updateProfileRequest);
    ResponseEntity<String> deleteJobPost(String email, Long postId);
}
