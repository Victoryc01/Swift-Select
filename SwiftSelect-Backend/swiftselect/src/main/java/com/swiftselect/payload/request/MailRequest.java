package com.swiftselect.payload.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailRequest {
    private String to;
    private String subject;
    private String message;
}
