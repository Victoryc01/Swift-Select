package com.swiftselect.payload.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
