package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.YetkiliSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.YetkiliResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Firma;
import com.pazarlamacitakip.pazarlamaci_backend.entity.User;
import com.pazarlamacitakip.pazarlamaci_backend.entity.UserRole;
import com.pazarlamacitakip.pazarlamaci_backend.repository.FirmaRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.UserRepository;
import com.pazarlamacitakip.pazarlamaci_backend.service.YetkiliService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/yetkililer")
@RequiredArgsConstructor
public class YetkiliController {

    private final YetkiliService yetkiliService;
    private final UserRepository userRepository;
    private final FirmaRepository firmaRepository;

    /**
     * Tüm yetkililer (sirketkodu bazlı izolasyon ile):
     * - DEVELOPER: Tümünü görür
     * - ADMIN/PERSONEL: Sadece kendi şirketine ait firmaların yetkililerini görür
     */
    @GetMapping
    public ResponseEntity<List<YetkiliResponse>> getAllYetkililer() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return ResponseEntity.status(401).build();
        }
        if (currentUser.getRole() == UserRole.DEVELOPER) {
            return ResponseEntity.ok(yetkiliService.getAllYetkililer());
        }
        return ResponseEntity.ok(yetkiliService.getYetkililerBySirketkodu(currentUser.getSirketkodu()));
    }

    /**
     * Firmaya ait yetkilileri getir.
     * Güvenlik Zinciri: Kullanıcının sirketkodu ile firmanın sirketkodu eşleşmeli.
     */
    @GetMapping("/firma/{firmaId}")
    public ResponseEntity<List<YetkiliResponse>> getByFirma(@PathVariable UUID firmaId) {
        User currentUser = getCurrentUser();
        if (currentUser != null && currentUser.getRole() != UserRole.DEVELOPER) {
            Firma firma = firmaRepository.findById(firmaId).orElse(null);
            if (firma == null || !currentUser.getSirketkodu().equals(firma.getSirketkodu())) {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.ok(yetkiliService.getYetkililerByFirmaId(firmaId));
    }

    /**
     * Yetkili Oluştur.
     * Güvenlik Zinciri: Kullanıcının sirketkodu ile yetkili eklenmek istenen firmanın sirketkodu aynı mı?
     */
    @PostMapping
    public ResponseEntity<YetkiliResponse> createYetkili(@RequestBody YetkiliSaveRequest request) {
        User currentUser = getCurrentUser();
        if (currentUser != null && currentUser.getRole() != UserRole.DEVELOPER) {
            // Güvenlik kontrolü: Firmanın sirketkodu ile kullanıcının sirketkodu eşleşmeli
            Firma firma = firmaRepository.findById(request.getFirmaId())
                    .orElseThrow(() -> new RuntimeException("Firma bulunamadı!"));
            if (!currentUser.getSirketkodu().equals(firma.getSirketkodu())) {
                return ResponseEntity.status(403).build();
            }
        }
        return ResponseEntity.ok(yetkiliService.createYetkili(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYetkili(@PathVariable UUID id) {
        yetkiliService.deleteYetkili(id);
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
