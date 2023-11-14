package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicenseRequest {
    @Size(min = 2, max = 25)
    @NotBlank(message = "required")
    String licenseName;

    @NotBlank(message = "required")
    String expirationDate;
}