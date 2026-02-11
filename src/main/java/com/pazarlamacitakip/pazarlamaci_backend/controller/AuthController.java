package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.AuthRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.CreateCompanyAdminRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.CreatePersonnelRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.AuthResponse;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.UserResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.entity.UserRole;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import com.pazarlamacitakip.pazarlamaci_backend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Giriş - Tüm roller için.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSifre())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // Aktif mi kontrolü
        if (!Boolean.TRUE.equals(user.getAktifmi())) {
            throw new RuntimeException("Hesabınız devre dışı bırakılmış!");
        }

        String token = generateTokenForUser(user, userDetails);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .adi(user.getAdi())
                .email(user.getEmail())
                .sirketkodu(user.getSirketkodu())
                .role(user.getRole().name())
                .build());
    }

    /**
     * Yeni Şirket Admini Oluştur - Sadece DEVELOPER çalıştırabilir.
     * Yeni bir UUID sirketkodu üretir ve Admin'e atar.
     */
    @PostMapping("/create-company-admin")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<UserResponse> createCompanyAdmin(@RequestBody CreateCompanyAdminRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email zaten kayıtlı!");
        }

        // Yeni şirket kodu üret (UUID)
        String sirketkodu = UUID.randomUUID().toString();

        User admin = new User();
        admin.setAdi(request.getAdi());
        admin.setEmail(request.getEmail());
        admin.setSifre(passwordEncoder.encode(request.getSifre()));
        admin.setTelefon(request.getTelefon());
        admin.setSirketkodu(sirketkodu);
        admin.setRole(UserRole.ADMIN);
        admin.setAktifmi(true);

        User savedAdmin = userRepository.save(admin);

        UserResponse response = mapToUserResponse(savedAdmin);
        return ResponseEntity.ok(response);
    }

    /**
     * Yeni Personel Oluştur - Sadece ADMIN (veya DEVELOPER) çalıştırabilir.
     * Admin'in kendi sirketkodunu personele kopyalar.
     */
    @PostMapping("/create-personnel")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    public ResponseEntity<UserResponse> createPersonnel(@RequestBody CreatePersonnelRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email zaten kayıtlı!");
        }

        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("Oturum bilgisi alınamadı!");
        }

        // Admin'in sirketkodunu personele kopyala
        String sirketkodu;
        if (currentUser.getRole() == UserRole.DEVELOPER) {
            // Developer herhangi bir şirketkodu belirleyemez — İsterse Admin olarak oluşturmalı
            throw new RuntimeException("Developer doğrudan personel oluşturamaz. Önce bir Admin oluşturun.");
        } else {
            sirketkodu = currentUser.getSirketkodu();
        }

        User personel = new User();
        personel.setAdi(request.getAdi());
        personel.setEmail(request.getEmail());
        personel.setSifre(passwordEncoder.encode(request.getSifre()));
        personel.setTelefon(request.getTelefon());
        personel.setBolge(request.getBolge());
        personel.setYer(request.getYer());
        personel.setPlaka(request.getPlaka());
        personel.setSirketkodu(sirketkodu);
        personel.setRole(UserRole.PERSONEL);
        personel.setAktifmi(true);

        User savedPersonel = userRepository.save(personel);

        UserResponse response = mapToUserResponse(savedPersonel);
        return ResponseEntity.ok(response);
    }

    // ---- Yardımcı Metodlar ----

    private String generateTokenForUser(User user, UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("sirketkodu", user.getSirketkodu());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("userId", user.getId().toString());
        return jwtService.generateToken(extraClaims, userDetails);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String email = auth.getName();
            return userRepository.findByEmail(email).orElse(null);
        }
        return null;
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setAdi(user.getAdi());
        response.setEmail(user.getEmail());
        response.setTelefon(user.getTelefon());
        response.setRole(user.getRole().name());
        response.setBolge(user.getBolge());
        response.setAktifmi(user.getAktifmi());
        response.setSirketkodu(user.getSirketkodu());
        response.setYer(user.getYer());
        response.setPlaka(user.getPlaka());
        return response;
    }
}
