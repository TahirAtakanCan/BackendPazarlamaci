package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.AuthRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.RegisterRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.AuthResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import com.pazarlamacitakip.pazarlamaci_backend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // Email zaten kayıtlı mı kontrol et
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email zaten kayıtlı!");
        }

        // Yeni kullanıcı oluştur
        User user = new User();
        user.setAdi(request.getAdi());
        user.setEmail(request.getEmail());
        user.setSifre(passwordEncoder.encode(request.getSifre())); // BCrypt ile şifrele!
        user.setTelefon(request.getTelefon());
        user.setSirketkodu(request.getSirketkodu());
        user.setAktifmi(true);
        user.setAdminmi(false);

        userRepository.save(user);

        // Token üret ve döndür
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSifre())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(userDetails);
        
        return ResponseEntity.ok(AuthResponse.builder().token(token).build());
    }
}
