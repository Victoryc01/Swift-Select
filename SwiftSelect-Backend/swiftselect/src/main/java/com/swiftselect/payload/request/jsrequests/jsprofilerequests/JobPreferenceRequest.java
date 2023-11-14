package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import com.swiftselect.domain.enums.Industry;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPreferenceRequest {
    @NotNull
    Industry industry;
}