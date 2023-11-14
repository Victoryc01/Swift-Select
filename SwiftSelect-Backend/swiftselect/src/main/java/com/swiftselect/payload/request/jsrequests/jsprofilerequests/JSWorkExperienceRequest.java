package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JSWorkExperienceRequest implements Serializable {
    @Size(message = "job title must be between 3 and 20", min = 3, max = 20)
    @NotBlank(message = "job title cannot be blank")
    String jobTitle;

    @Size(message = "company name must be between 1 and 30", min = 1, max = 30)
    @NotBlank(message = "company name cannot be blank")
    String companyName;

    @NotNull(message = "start date cannot be null")
    @PastOrPresent
    LocalDate startDate;

    @PastOrPresent
    LocalDate stopDate;
}