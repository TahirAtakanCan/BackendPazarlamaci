package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.KonumSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.service.PersonelKonumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/konum")
@RequiredArgsConstructor
public class PersonelKonumController {

    private final PersonelKonumService service;

    @PostMapping
    public ResponseEntity<Void> saveKonum(@RequestBody KonumSaveRequest request) {
        service.saveKonum(request);
        return ResponseEntity.ok().build();
    }
}
