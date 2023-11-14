package com.swiftselect.payload.request.employerreqests;

import com.swiftselect.domain.enums.CompanyType;
import com.swiftselect.domain.enums.Industry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployerUpdateProfileRequest {
    @Size(min = 2, max = 25, message = "company name must be at least 2 characters")
    @NotBlank(message = "company name must not be blank")
    String companyName;

    @Size(min = 10, max = 150, message = "company description must be at least 10 characters")
    @NotBlank(message = "company name must not be blank")
    String companyDescription;

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

    @NotNull(message = "company type cannot be null")
    Industry industry;

    @NotNull(message = "company type cannot be null")
    CompanyType companyType;

    String postalCode;

    Long numberOfEmployees;

    String website;

    String facebook;

    String twitter;

    String instagram;

    @Size(min = 2, max = 25, message = "firstName must be at least 2 characters")
    @NotBlank(message = "firstName must not be blank")
    String firstName;

    @Size(min = 2, max = 25, message = "lastName must be at least 2 characters")
    @NotBlank(message = "lastName must not be blank")
    String lastName;

    @Size(min = 6, max = 15, message = "phone number must be at least 6 characters")
    @NotBlank(message = "Please input a valid number")
    String phoneNumber;

    @Size(min = 2, max = 25, message = "position must be at least 2 characters")
    @NotBlank(message = "position must not be blank")
    String position;
}
