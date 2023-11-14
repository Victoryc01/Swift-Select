package com.swiftselect.infrastructure.controllers.authcontroller;

import com.swiftselect.infrastructure.exceptions.ApplicationException;
import com.swiftselect.payload.request.authrequests.ForgotPasswordResetRequest;
import com.swiftselect.payload.request.authrequests.UserLogin;
import com.swiftselect.payload.request.employerreqests.EmployerSignup;
import com.swiftselect.payload.request.jsrequests.JobSeekerSignup;
import com.swiftselect.payload.response.JwtAuthResponse;
import com.swiftselect.services.AuthService;
import com.swiftselect.services.EmployerService;
import com.swiftselect.services.JobSeekerService;
import com.swiftselect.utils.AuthenticationUtils;
import com.swiftselect.utils.HelperClass;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JobSeekerService jobSeekerService;
    private final EmployerService employerService;
    private final AuthService authService;
    private final HelperClass helperClass;

    @PostMapping("/job-seeker/register")
    public ResponseEntity<String> registerJobSeeker(@Valid @RequestBody JobSeekerSignup jobSeekerDto) {
        return authService.registerJobSeeker(jobSeekerDto);
    }

    @PostMapping("/employer/register")
    public ResponseEntity<String> registerEmployer(@Valid @RequestBody EmployerSignup employerSignup) {
        return authService.registerEmployer(employerSignup);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody UserLogin userLogin) {
        return authService.login(userLogin);
    }

    @GetMapping("/register/verify-email")
    public ResponseEntity<String> verifyToken(@RequestParam("token") String token) {

        return authService.validateToken(token);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        return authService.forgotPassword(email);
    }

    @GetMapping(value = "/forgot-password/reset-password-page", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> resetPasswordPage(@RequestParam("email") String email,
                                                    @RequestParam("token") String token,
                                                    final HttpServletRequest request) {

        ResponseEntity<String> result = authService.validateTokenForgotPassword(token);

        if (!Objects.equals(result.getBody(), "Valid")) {
            throw new ApplicationException(result.getBody(), HttpStatus.BAD_REQUEST);
        }

        String action = "SwiftSelect | Password Change";
        String serviceProvider = "Swift Select Customer Portal Service";
        String url = AuthenticationUtils.applicationUrl(request) + "/auth/success";
        String description = "Please provide the details below to change your password.";

        return ResponseEntity.ok(helperClass.restPasswordHtml(token, email, url, action, serviceProvider, description));
    }

    @GetMapping(value = "/success")
    public ResponseEntity<String> success(@RequestParam("token") String token,
                                          @RequestParam("newPassword") String newPassword,
                                          @RequestParam("confirmNewPassword") String confirmNewPassword) {

        ForgotPasswordResetRequest forgotPasswordResetRequest = new ForgotPasswordResetRequest(token, newPassword, confirmNewPassword);

        return authService.resetForgotPassword(forgotPasswordResetRequest);
    }
}
