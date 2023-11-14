package com.swiftselect.payload.request.authrequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    @Size(min = 6, max = 15, message = "password must be at least 6 characters")
    @NotBlank(message = "password must not be blank")
    private String oldPassword;

    @Size(min = 6, max = 15, message = "password must be at least 6 characters")
    @NotBlank(message = "password must not be blank")
    private String newPassword;

    @Size(min = 6, max = 15, message = "password must be at least 6 characters")
    @NotBlank(message = "password must not be blank")
    private String confirmNewPassword;
}
