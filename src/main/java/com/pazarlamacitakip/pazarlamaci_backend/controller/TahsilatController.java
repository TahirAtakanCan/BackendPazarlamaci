package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.TahsilatSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.TahsilatResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.TahsilatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tahsilatlar")
@RequiredArgsConstructor
public class TahsilatController {

    private final TahsilatService tahsilatService;

    @GetMapping
    public ResponseEntity<List<TahsilatResponse>> getAll() {
        return ResponseEntity.ok(tahsilatService.getAll());
    }

    @PostMapping
    public ResponseEntity<TahsilatResponse> create(@RequestBody TahsilatSaveRequest request) {
        return ResponseEntity.ok(tahsilatService.createTahsilat(request));
    }

    @GetMapping("/personel/{personelId}")
    public ResponseEntity<List<TahsilatResponse>> getByPersonel(@PathVariable UUID personelId) {
        return ResponseEntity.ok(tahsilatService.getByPersonel(personelId));
    }
}
