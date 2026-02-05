package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.IsharSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.IsharResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Ishar;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Job;
import com.pazarlamacitakip.pazarlamaci_backend.repository.IsharRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IsharService {

    private final IsharRepository isharRepository;
    private final JobRepository jobRepository;

    public IsharResponse createIshar(IsharSaveRequest request) {
        // İlgili Job var mı kontrol et
        Job job = jobRepository.findById(request.getIsId())
                .orElseThrow(() -> new RuntimeException("Bağlı iş bulunamadı!"));

        Ishar ishar = new Ishar();
        ishar.setIsId(request.getIsId());
        ishar.setGorevAdi(request.getGorevAdi());
        ishar.setGorevAciklama(request.getGorevAciklama());
        ishar.setGorevNotu(request.getGorevNotu());
        ishar.setDurum(request.getDurum());
        
        // Tarih şu an olsun
        ishar.setTarih(LocalDateTime.now());
        ishar.setDuzenlenmeTarihi(LocalDateTime.now());

        // Finansal
        ishar.setTahsilTutari(request.getTahsilTutari());
        ishar.setTahsilEdilenTutar(request.getTahsilEdilenTutar());
        ishar.setOdemeTuru(request.getOdemeTuru());
        ishar.setBelgeUrl(request.getBelgeUrl());

        Ishar saved = isharRepository.save(ishar);
        
        // Opsiyonel: Ishar eklendiğinde Job'ın durumunu güncelle (1: Tamamlandı gibi)
        // job.setDurum(1);
        // jobRepository.save(job);
        
        return mapToResponse(saved);
    }

    public List<IsharResponse> getIsharByJobId(UUID jobId) {
        return isharRepository.findByIsId(jobId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private IsharResponse mapToResponse(Ishar ishar) {
        return IsharResponse.builder()
                .id(ishar.getId())
                .isId(ishar.getIsId())
                .gorevAdi(ishar.getGorevAdi())
                .gorevAciklama(ishar.getGorevAciklama())
                .gorevNotu(ishar.getGorevNotu())
                .durum(ishar.getDurum())
                .tarih(ishar.getTarih())
                .tahsilTutari(ishar.getTahsilTutari())
                .tahsilEdilenTutar(ishar.getTahsilEdilenTutar())
                .odemeTuru(ishar.getOdemeTuru())
                .belgeUrl(ishar.getBelgeUrl())
                .build();
    }
}
