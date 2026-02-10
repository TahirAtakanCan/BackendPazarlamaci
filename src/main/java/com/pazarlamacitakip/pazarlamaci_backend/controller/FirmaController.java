package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.FirmaSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import com.pazarlamacitakip.pazarlamaci_backend.service.FirmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/firmalar")
@RequiredArgsConstructor
public class FirmaController {

    private final FirmaService firmaService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<FirmaResponse>> getAllFirmas() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status(401).build();
        }
        // Developer tüm firmaları görür
        if ("DEVELOPER".equals(currentUser.getYetki())) {
            return ResponseEntity.ok(firmaService.getAllFirmas());
        }
        // Yönetici sadece kendi şirketinin firmalarını görür
        return ResponseEntity.ok(firmaService.getFirmasBySirketkodu(currentUser.getSirketkodu()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FirmaResponse> getFirmaById(@PathVariable UUID id) {
        return ResponseEntity.ok(firmaService.getFirmaById(id));
    }

    @PostMapping
    public ResponseEntity<FirmaResponse> createFirma(@RequestBody FirmaSaveRequest request) {
        return ResponseEntity.ok(firmaService.createFirma(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFirma(@PathVariable UUID id) {
        firmaService.deleteFirma(id);
        return ResponseEntity.ok().build();
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String email = auth.getName();
            return userRepository.findByEmail(email).orElse(null);
        }
        return null;
    }
}
