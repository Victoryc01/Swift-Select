package com.swiftselect.infrastructure.event.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NotificationMailEvent extends ApplicationEvent {
    private String firstName;
    private String email;
    private String subject;
    private String description;
    private String applicationUrl;

    public NotificationMailEvent(String email, String firstName, String subject, String description, String applicationUrl) {
        super(email);
        this.firstName = firstName;
        this.email = email;
        this.subject = subject;
        this.applicationUrl = applicationUrl;
        this.description = description;
    }
}
