package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.FirmaSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.FirmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/firmalar")
@RequiredArgsConstructor
public class FirmaController {

    private final FirmaService firmaService;

    @GetMapping
    public ResponseEntity<List<FirmaResponse>> getAllFirmas() {
        return ResponseEntity.ok(firmaService.getAllFirmas());
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
}
