package com.screeningTest.screening.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.signerKey}")
    private String signerKey;
    String adminRole = "SCOPE_ADMIN";
    String userRole = "ROLE_USER";
    private final String[] PUBLIC_ENPOINTS = {"/auth", "/auth/log-in",
            "/auth/token", "/auth/introspect", "/account"};
    private final String[] ADMIN_PRIVATE_ENPOINTS = {"/teacher/**", "/contract/**", "/degree/**"
                                               };@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSercurity) throws Exception {
        httpSercurity.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.POST, PUBLIC_ENPOINTS).permitAll()
                        .requestMatchers(HttpMethod.DELETE, ADMIN_PRIVATE_ENPOINTS).hasAnyAuthority(adminRole)
                        .anyRequest().authenticated());

        //dang ky
        httpSercurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
                );

        httpSercurity.csrf(AbstractHttpConfigurer::disable);

        return httpSercurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec key = new SecretKeySpec(signerKey.getBytes(), "HS512");

        return NimbusJwtDecoder.withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
