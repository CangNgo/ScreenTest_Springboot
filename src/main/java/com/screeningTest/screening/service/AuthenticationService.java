package com.screeningTest.screening.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.screeningTest.screening.dto.request.Authentication.AuthenticationRequest;
import com.screeningTest.screening.dto.request.Authentication.IntrospectRequest;
import com.screeningTest.screening.dto.response.AuthenticationResponse;
import com.screeningTest.screening.dto.response.IntrospectResponse;
import com.screeningTest.screening.entity.Account;
import com.screeningTest.screening.exception.AppException;
import com.screeningTest.screening.exception.ErrorCode;
import com.screeningTest.screening.repository.IAccountReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    IAccountReponsitory accountReponsitory;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY ;

    public IntrospectResponse introspect(IntrospectRequest request) throws AppException, JOSEException, ParseException {
        var token =request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        // lấy thòi gian tồn tại của token
        Date expirttime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verifield = signedJWT.verify(verifier);

        return  IntrospectResponse.builder()
                .valid(verifield && expirttime.after(new Date()))
                .build();
    }

    public AuthenticationResponse Authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var user = accountReponsitory.findByUsername(request.getUsername()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED)
        );

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(Account user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("screen.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope" , user.isRole()?"ADMIN":"USER")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        // sign token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }

    }


}
