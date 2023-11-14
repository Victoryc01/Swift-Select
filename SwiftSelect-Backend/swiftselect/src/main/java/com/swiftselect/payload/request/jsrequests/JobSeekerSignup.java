package com.swiftselect.payload.request.jsrequests;

import com.swiftselect.domain.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerSignup {
    @Size(min = 2, max = 25, message = "name must be at least 2 characters")
    @NotBlank(message = "name must not be blank")
    String firstName;

    @Size(min = 2, max = 25, message = "name must be at least 2 characters")
    @NotBlank(message = "name must not be blank")
    String lastName;

    @Size(min = 6, max = 15, message = "password must be at least 6 characters")
    @NotBlank(message = "password must not be blank")
    String password;

    @Size(min = 6, max = 15, message = "phone number must be at least 6 characters")
    @NotBlank(message = "Please input a valid number")
    String phoneNumber;

    @NotBlank(message = "Location required")
    String address;

    @NotBlank(message = "Input valid country")
    String country;

    @Size(min = 8, max = 50)
    @Email(message = "E-mail must be valid")
    @NotBlank(message = "E-mail required")
    String email;

    @NotNull
    Gender gender;

    String dateOfBirth;
}