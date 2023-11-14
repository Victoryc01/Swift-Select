package com.swiftselect.payload.request.jsrequests.jsprofilerequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JSLocationInfoRequest {
    @Size(min = 3, max = 35, message = "address must be at least 3 characters")
    @NotBlank(message = "Address is required")
    String address;

    @Size(min = 3, max = 25, message = "country must be at least 3 characters")
    @NotBlank(message = "Country cannot be blank")
    String country;

    @Size(min = 3, max = 25, message = "state must be at least 3 characters")
    @NotBlank(message = "State cannot be empty")
    String state;

    @Size(min = 3, max = 25, message = "city must be at least 3 characters")
    @NotBlank(message = "City cannot be empty")
    String city;

    String postalCode;
}
