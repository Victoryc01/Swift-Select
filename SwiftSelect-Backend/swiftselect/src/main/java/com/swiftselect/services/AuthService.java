package com.swiftselect.services;

import com.swiftselect.payload.request.authrequests.ForgotPasswordResetRequest;
import com.swiftselect.payload.request.authrequests.UserLogin;
import com.swiftselect.payload.request.employerreqests.EmployerSignup;
import com.swiftselect.payload.request.jsrequests.JobSeekerSignup;
import com.swiftselect.payload.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    void saveVerificationToken(String email, String token);
    ResponseEntity<String> registerJobSeeker(JobSeekerSignup jobSeekerSignup);
    ResponseEntity<String> registerEmployer(EmployerSignup employerSignup);
    ResponseEntity<JwtAuthResponse> login(UserLogin userLogin);
    ResponseEntity<String> forgotPassword(String email);
    ResponseEntity<String> validateToken(String receivedToken);
    ResponseEntity<String> validateTokenForgotPassword(String receivedToken);
    ResponseEntity<String> resetForgotPassword(ForgotPasswordResetRequest forgotPasswordResetRequest);
}
