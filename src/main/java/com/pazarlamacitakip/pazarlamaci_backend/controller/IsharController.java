package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.IsharSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.IsharUpdateRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.IsharResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.IsharService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ishar")
@RequiredArgsConstructor
public class IsharController {

    private final IsharService isharService;

    @PostMapping
    public ResponseEntity<IsharResponse> create(@RequestBody IsharSaveRequest request) {
        return ResponseEntity.ok(isharService.createIshar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IsharResponse> updateIshar(@PathVariable UUID id, @RequestBody IsharUpdateRequest request) {
        return ResponseEntity.ok(isharService.updateIshar(id, request));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<IsharResponse>> getByJob(@PathVariable UUID jobId) {
        return ResponseEntity.ok(isharService.getIsharByJobId(jobId));
    }
}
