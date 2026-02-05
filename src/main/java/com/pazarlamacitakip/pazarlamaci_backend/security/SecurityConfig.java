package com.pazarlamacitakip.pazarlamaci_backend.security;

import com.pazarlamacitakip.pazarlamaci_backend.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Login herkese açık
                        .requestMatchers("/api/yetkililer/**").permitAll() // Test için açık
                        .requestMatchers("/api/jobs/**").permitAll() // Test için açık
                        .requestMatchers("/api/firmalar/**").permitAll() // Test için açık
                        .requestMatchers("/api/users/**").permitAll() // Test için açık
                        .requestMatchers("/api/task-defs/**").permitAll() // Test için açık
                        .requestMatchers("/api/ishar/**").permitAll() // Test için açık
                        .requestMatchers("/api/harcamalar/**").permitAll() // Test için açık
                        .requestMatchers("/api/konum/**").permitAll() // Test için açık
                        .requestMatchers("/api/notes/**").permitAll() // Test için açık
                        .requestMatchers("/api/tahsilatlar/**").permitAll() // Test için açık
                        .requestMatchers("/api/files/**").permitAll() // Dosya yükleme/indirme
                        .anyRequest().authenticated() // Diğer her yer token ister
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
