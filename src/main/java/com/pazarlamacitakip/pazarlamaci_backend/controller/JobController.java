package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.JobSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.JobUpdateRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.JobResponse;
import com.pazarlamacitakip.pazarlamaci_backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@RequestBody JobSaveRequest request) {
        return ResponseEntity.ok(jobService.createJob(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable UUID id, @RequestBody JobUpdateRequest request) {
        return ResponseEntity.ok(jobService.updateJob(id, request));
    }

    @GetMapping("/personel/{personelId}")
    public ResponseEntity<List<JobResponse>> getJobsByPersonel(@PathVariable UUID personelId) {
        return ResponseEntity.ok(jobService.getJobsByPersonel(personelId));
    }
}
