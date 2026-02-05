package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.HarcamaSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.HarcamaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.HarcamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/harcamalar")
@RequiredArgsConstructor
public class HarcamaController {

    private final HarcamaService harcamaService;

    @PostMapping
    public ResponseEntity<HarcamaResponse> create(@RequestBody HarcamaSaveRequest request) {
        return ResponseEntity.ok(harcamaService.createHarcama(request));
    }

    @GetMapping("/personel/{personelId}")
    public ResponseEntity<List<HarcamaResponse>> getByPersonel(@PathVariable UUID personelId) {
        return ResponseEntity.ok(harcamaService.getByPersonel(personelId));
    }
}
