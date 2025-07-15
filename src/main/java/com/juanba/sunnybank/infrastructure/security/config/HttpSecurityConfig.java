package com.juanba.sunnybank.infrastructure.security.config;

import com.juanba.sunnybank.domain.model.user.Role;
import com.juanba.sunnybank.infrastructure.security.config.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class HttpSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(httpRequest -> {
                    // ! Public endpoints
                    httpRequest.requestMatchers(HttpMethod.POST, "/api/v1/authenticate").permitAll();

                    // ! User endpoints
                    httpRequest.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll();
                    httpRequest.requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").hasAnyRole(Role.SYSTEM_ADMIN.name(), Role.BANK_EMPLOYEE.name());
                    httpRequest.requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyRole(Role.SYSTEM_ADMIN.name(), Role.BANK_EMPLOYEE.name());
                    httpRequest.requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasRole(Role.SYSTEM_ADMIN.name());
                    httpRequest.requestMatchers(HttpMethod.PUT, "/api/v1/users").hasAnyRole(Role.SYSTEM_ADMIN.name(), Role.BANK_EMPLOYEE.name(), Role.CUSTOMER.name());
                    httpRequest.requestMatchers(HttpMethod.GET, "/api/v1/users/{id}/generate-code-password").hasAnyRole(Role.SYSTEM_ADMIN.name(), Role.CUSTOMER.name());
                    httpRequest.requestMatchers(HttpMethod.PUT, "/api/v1/users/change-password").hasAnyRole(Role.SYSTEM_ADMIN.name(), Role.CUSTOMER.name());

                    // ! Bank account endpoints
                    httpRequest.requestMatchers(HttpMethod.POST, "/api/v1/bank-accounts").hasAnyRole(Role.SYSTEM_ADMIN.name(), Role.BANK_EMPLOYEE.name());


                    httpRequest.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
