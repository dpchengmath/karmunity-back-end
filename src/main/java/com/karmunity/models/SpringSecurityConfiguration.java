package com.karmunity.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/api/members/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/members/create").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/members/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/members/**").authenticated()
                .anyRequest().authenticated()
                .and().formLogin()
                .permitAll()
                .and().httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .roles("ADMIN"))
                .withUser(User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .roles("USER"));

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}