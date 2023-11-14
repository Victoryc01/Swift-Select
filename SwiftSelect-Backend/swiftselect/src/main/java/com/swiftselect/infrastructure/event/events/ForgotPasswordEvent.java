package com.swiftselect.infrastructure.event.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ForgotPasswordEvent extends ApplicationEvent {
    private String email;
    private String applicationUrl;

    public ForgotPasswordEvent(String email, String applicationUrl) {
        super(email);
        this.email = email;
        this.applicationUrl = applicationUrl;
    }
}
