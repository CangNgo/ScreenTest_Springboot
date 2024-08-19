package com.screeningTest.screening.dto.request.Authentication;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class AuthenticationRequest {
    String username;
    String password;
    boolean role;
}
