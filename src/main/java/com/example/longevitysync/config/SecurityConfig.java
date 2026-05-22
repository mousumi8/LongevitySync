package com.example.longevitysync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/**").permitAll()  // Allow all /api/** endpoints without authentication
                .anyRequest().permitAll()  // Allow all other requests without authentication
            )
            .csrf(csrf -> csrf.disable())  // Disable CSRF for API endpoints
            .formLogin(form -> form.disable())  // Disable form-based login
            .httpBasic(basic -> basic.disable());  // Disable HTTP basic auth

        return http.build();
    }
}

