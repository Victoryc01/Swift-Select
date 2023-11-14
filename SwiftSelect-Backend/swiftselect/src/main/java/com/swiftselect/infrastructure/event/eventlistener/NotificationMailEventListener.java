package com.swiftselect.infrastructure.event.eventlistener;

import com.swiftselect.infrastructure.event.events.NotificationMailEvent;
import com.swiftselect.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Async
@Component
@RequiredArgsConstructor
public class NotificationMailEventListener implements ApplicationListener<NotificationMailEvent> {
    private final EmailSenderService emailSenderService;
    @Override
    public void onApplicationEvent(NotificationMailEvent event) {
        String url = "mailto:swiftselect12@gmail.com";
        String email = event.getEmail();
        String firstName = event.getFirstName();
        String subject = event.getSubject();
        String description = event.getDescription();

        emailSenderService.sendNotificationEmail(url, email, firstName, subject, description);
    }
}
