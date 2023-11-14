package com.swiftselect.infrastructure.event.eventlistener;

import com.swiftselect.infrastructure.event.events.ForgotPasswordEvent;
import com.swiftselect.services.AuthService;
import com.swiftselect.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Async
@Component
@RequiredArgsConstructor
public class ForgotPasswordEventListener implements ApplicationListener<ForgotPasswordEvent> {
    private final EmailSenderService emailSenderService;
    private final AuthService authService;

    @Override
    public void onApplicationEvent(ForgotPasswordEvent event) {

        // Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();

        // Save the verification token for the user
        authService.saveVerificationToken(event.getEmail(), verificationToken);

        // Build the verification url to be sent to the jobSeeker
        String url = event.getApplicationUrl() + "/auth/forgot-password/reset-password-page?email=" + event.getEmail() + "&token=" + verificationToken;

        // Send the email to the jobSeeker
        emailSenderService.sendForgotPasswordEmailVerification(url, event);

        log.info("Click the link to verify your email and change ur password : {}", url);
    }
}
