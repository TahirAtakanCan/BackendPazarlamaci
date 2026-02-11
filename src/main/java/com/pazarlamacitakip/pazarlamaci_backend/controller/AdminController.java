package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.FirmaWithYoneticiRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaWithYoneticiResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DEVELOPER')") // Tüm admin endpointleri sadece Developer'a açık
public class AdminController {

    private final AdminService adminService;

    // Şirket + Yönetici birlikte oluştur (sadece Developer)
    @PostMapping("/firma-with-yonetici")
    public ResponseEntity<FirmaWithYoneticiResponse> createFirmaWithYonetici(
            @RequestBody FirmaWithYoneticiRequest request) {
        return ResponseEntity.ok(adminService.createFirmaWithYonetici(request));
    }

    // Şirket sil (bağlı yetkilileri de siler)
    @DeleteMapping("/firma/{id}")
    public ResponseEntity<Void> deleteFirma(@PathVariable UUID id) {
        adminService.deleteFirmaHard(id);
        return ResponseEntity.ok().build();
    }

    // Yönetici (User) sil
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        adminService.deleteUserHard(id);
        return ResponseEntity.ok().build();
    }
}
