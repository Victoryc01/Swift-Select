package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import com.swiftselect.domain.enums.JobType;
import com.swiftselect.domain.enums.PayRate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobExpectationsRequest {
    @NotBlank(message = "required")
    String workSchedule;

    @NotBlank(message = "required")
    String basePay;

    @NotNull(message = "required")
    JobType jobType;

    @NotNull(message = "required")
    PayRate payRate;
}