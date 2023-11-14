package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import com.swiftselect.domain.enums.EducationLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequest {
    @NotNull(message = "required")
    EducationLevel educationLevel;

    @Size(message = "field of study must be between 2 and 30", min = 2, max = 30)
    @NotBlank(message = "field of study cannot be blank")
    String fieldOfStudy;

    @NotBlank(message = "required")
    String yearOfGraduation;
}