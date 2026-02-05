package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.YetkiliSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.YetkiliResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.YetkiliService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/yetkililer")
@RequiredArgsConstructor
public class YetkiliController {

    private final YetkiliService yetkiliService;

    @GetMapping("/firma/{firmaId}")
    public ResponseEntity<List<YetkiliResponse>> getByFirma(@PathVariable UUID firmaId) {
        return ResponseEntity.ok(yetkiliService.getYetkililerByFirmaId(firmaId));
    }

    @PostMapping
    public ResponseEntity<YetkiliResponse> createYetkili(@RequestBody YetkiliSaveRequest request) {
        return ResponseEntity.ok(yetkiliService.createYetkili(request));
    }
}
