package com.karmunity.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SpringSecurityConfiguration {

    /** 🔐 1. Define User Authentication (Instead of inMemoryAuthentication) */
    @Bean
    public UserDetailsService userDetailsService() {
        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .roles("USER")
                        .build()
        );
    }

    /** 🔐 2. Define AuthenticationProvider */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /** 🔐 3. AuthenticationManager Bean */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /** 🔐 4. Password Encoder */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 🌍 5. Define CORS Configuration */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173")); // Update if needed
        corsConfiguration.setAllowedMethods(List.of("POST", "GET", "PUT", "PATCH", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    /** 🔐 6. Define Security Filter Chain */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for REST APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/members/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/karmunities/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/karma/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/members/").authenticated()
                        .requestMatchers(HttpMethod.POST, "/karmunities/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/karma/").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/members/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/karmunities/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/karma/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/members/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/karmunities/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/karma/**").authenticated()
                        .anyRequest().authenticated()  // Secure all other endpoints
                )
                .authenticationProvider(authenticationProvider()) // Use custom authentication provider
                .httpBasic(httpBasic -> {}) // Enable HTTP Basic authentication
                .build();
    }
}