package com.gameshop.gameservice.config;

import com.gameshop.gameservice.security.JwtUtil;
import com.gameshop.gameservice.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // ----------------------
    // DEPENDENCIES
    // ----------------------

    // Inject the custom JWT filter so we can add it into the Spring Security chain.
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    // ----------------------
    // SECURITY FILTER CHAIN
    // ----------------------

    /**
     * The SecurityFilterChain defines how security is applied to incoming HTTP requests.
     * - Configures which endpoints are open or protected
     * - Disables features not needed in stateless JWT-based authentication
     * - Registers our custom JwtAuthenticationFilter in the chain
     *
     * @param http The HttpSecurity builder that defines security behavior
     * @return A built SecurityFilterChain used by Spring at runtime
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/users/register", "/users/login", "/health").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}