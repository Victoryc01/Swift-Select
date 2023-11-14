package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageRequest {
    @NotBlank(message = "required")
    String language;

    @NotBlank
    String proficiency;
}