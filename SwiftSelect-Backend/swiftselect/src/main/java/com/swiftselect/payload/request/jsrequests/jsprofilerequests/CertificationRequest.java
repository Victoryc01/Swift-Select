package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationRequest {
    @Size(min = 1, max = 30)
    @NotBlank(message = "required")
    String name;

    @NotBlank(message = "required")
    String expiration;
}