package com.pazarlamacitakip.pazarlamaci_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // İzin verilen origin'ler (Frontend URL'leri)
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",    // React/Next.js default
            "http://localhost:5173",    // Vite default
            "http://localhost:4200",    // Angular default
            "http://localhost:8080",    // Vue default
            "http://127.0.0.1:3000",
            "http://127.0.0.1:5173"
            // Production'da buraya gerçek domain eklenecek
            // "https://pazarlamaci.com"
        ));
        
        // İzin verilen HTTP metodları
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
        // İzin verilen header'lar
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Requested-With",
            "Accept",
            "Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        
        // Response'da görünecek header'lar
        configuration.setExposedHeaders(Arrays.asList(
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials",
            "Authorization"
        ));
        
        // Credential'ları (Cookie, Authorization header) kabul et
        configuration.setAllowCredentials(true);
        
        // Preflight cache süresi (saniye)
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
