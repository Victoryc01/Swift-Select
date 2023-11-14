package com.swiftselect.payload.request.authrequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequest {
    @Size(min = 8, max = 50)
    @Email(message = "E-mail must be valid")
    @NotBlank(message = "E-mail required")
    String email;
}
