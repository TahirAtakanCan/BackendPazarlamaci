package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.FirmaSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.entity.UserRole;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import com.pazarlamacitakip.pazarlamaci_backend.service.FirmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Firma Listeleme - sirketkodu bazlı veri izolasyonu:
     * - DEVELOPER: Tüm firmaları görür
     * - ADMIN/PERSONEL: Sadece kendi sirketkoduyla eşleşen firmaları görür
     */
    @GetMapping
    public ResponseEntity<List<FirmaResponse>> getAllFirmas() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status(401).build();
        }
        if (currentUser.getRole() == UserRole.DEVELOPER) {
            return ResponseEntity.ok(firmaService.getAllFirmas());
        }
        return ResponseEntity.ok(firmaService.getFirmasBySirketkodu(currentUser.getSirketkodu()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FirmaResponse> getFirmaById(@PathVariable UUID id) {
        User currentUser = getCurrentUser();
        FirmaResponse firma = firmaService.getFirmaById(id);
        // Veri izolasyonu: Developer hariç, kullanıcının şirketkodu ile firma şirketkodu eşleşmeli
        if (currentUser != null && currentUser.getRole() != UserRole.DEVELOPER) {
            if (!currentUser.getSirketkodu().equals(firma.getSirketkodu())) {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.ok(firma);
    }

    /**
     * Firma Oluşturma - sirketkodu otomatik olarak mevcut kullanıcıdan alınır.
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('DEVELOPER', 'ADMIN', 'PERSONEL')")
    public ResponseEntity<FirmaResponse> createFirma(@RequestBody FirmaSaveRequest request) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status(401).build();
        }
        // Otomatik sirketkodu ataması
        if (currentUser.getRole() != UserRole.DEVELOPER) {
            request.setSirketkodu(currentUser.getSirketkodu());
        }
        // Developer ise request'teki sirketkodu kullanılır (veya boşsa hata)
        return ResponseEntity.ok(firmaService.createFirma(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('DEVELOPER', 'ADMIN')")
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
