package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JSContactInfoRequest {
    @Size(min = 2, max = 25, message = "firstName must be at least 2 characters")
    @NotBlank(message = "firstName must not be blank")
    String firstName;

    @Size(min = 2, max = 25, message = "lastName must be at least 2 characters")
    @NotBlank(message = "lastName must not be blank")
    String lastName;

    @Size(min = 6, max = 15, message = "phone number must be at least 6 characters")
    @NotBlank(message = "Please input a valid number")
    String phoneNumber;
}
