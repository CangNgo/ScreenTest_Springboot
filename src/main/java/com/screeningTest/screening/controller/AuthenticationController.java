package com.screeningTest.screening.controller;

import com.nimbusds.jose.JOSEException;
import com.screeningTest.screening.dto.request.ApiResponse;
import com.screeningTest.screening.dto.request.Authentication.AuthenticationRequest;
import com.screeningTest.screening.dto.request.Authentication.IntrospectRequest;
import com.screeningTest.screening.dto.response.AuthenticationResponse;
import com.screeningTest.screening.dto.response.IntrospectResponse;
import com.screeningTest.screening.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
       var result =  authenticationService.Authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result =  authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
