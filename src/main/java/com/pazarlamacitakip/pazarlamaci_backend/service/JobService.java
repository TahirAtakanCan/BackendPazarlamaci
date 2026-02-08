package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.JobSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.JobResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Firma;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Job;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Yetkili;
import com.pazarlamacitakip.pazarlamaci_backend.repository.FirmaRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.JobRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.YetkiliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final FirmaRepository firmaRepository;
    private final YetkiliRepository yetkiliRepository;

    public JobResponse createJob(JobSaveRequest request) {
        Job job = new Job();
        
        // 1. İlişkili verileri bul
        Firma firma = firmaRepository.findById(request.getFirmaId())
                .orElseThrow(() -> new RuntimeException("Firma bulunamadı"));
        
        // 2. Temel alanları set et
        job.setPersonelId(request.getPersonelId());
        job.setFirmaId(request.getFirmaId());
        job.setYetkiliId(request.getYetkiliId());
        
        // 3. DENORMALİZASYON (Snapshot)
        // Firma adını o anki haliyle Job tablosuna yazıyoruz.
        job.setFirmaAdi(firma.getAdi());
        
        // Yetkili varsa onun da adını yaz
        if (request.getYetkiliId() != null) {
            Yetkili yetkili = yetkiliRepository.findById(request.getYetkiliId())
                    .orElseThrow(() -> new RuntimeException("Yetkili bulunamadı"));
            job.setYetkiliAdi(yetkili.getAdi() + " " + yetkili.getSoyadi());
        }

        job.setGorevAdi(request.getGorevAdi());
        job.setGorevTuru(request.getGorevTuru());
        job.setOncelik(request.getOncelik());
        job.setTarih(request.getTarih());
        job.setTahminiSure(request.getTahminiSure());
        
        job.setDurum(0); // Varsayılan: Beklemede
        job.setIlkTarih(LocalDateTime.now()); // Kayıt tarihi

        Job savedJob = jobRepository.save(job);
        return mapToResponse(savedJob);
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<JobResponse> getJobsByPersonel(UUID personelId) {
        return jobRepository.findByPersonelId(personelId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JobResponse updateJob(UUID id, com.pazarlamacitakip.pazarlamaci_backend.dto.request.JobUpdateRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job bulunamadı: " + id));

        if (request.getDurum() != null) {
            job.setDurum(request.getDurum());
        }
        if (request.getTamamlanmaTarihi() != null) {
            job.setTamamlanmaTarihi(request.getTamamlanmaTarihi());
        }

        Job updatedJob = jobRepository.save(job);
        return mapToResponse(updatedJob);
    }

    private JobResponse mapToResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .firmaAdi(job.getFirmaAdi())     // Direkt Job tablosundan okuyoruz (Hızlı)
                .yetkiliAdi(job.getYetkiliAdi()) // Direkt Job tablosundan okuyoruz
                .gorevAdi(job.getGorevAdi())
                .gorevTuru(job.getGorevTuru())
                .oncelik(job.getOncelik())
                .durum(job.getDurum())
                .tarih(job.getTarih())
                .tamamlanmaTarihi(job.getTamamlanmaTarihi())
                .build();
    }
}
