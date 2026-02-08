package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.HarcamaSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.HarcamaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Harcama;
import com.pazarlamacitakip.pazarlamaci_backend.repository.HarcamaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarcamaService {

    private final HarcamaRepository harcamaRepository;

    public HarcamaResponse createHarcama(HarcamaSaveRequest request) {
        Harcama harcama = new Harcama();
        harcama.setPersonelId(request.getPersonelId());
        harcama.setTutar(request.getTutar());
        harcama.setTur(request.getTur());
        harcama.setHarcamaNotu(request.getHarcamaNotu());
        harcama.setBelgeUrl(request.getBelgeUrl());
        harcama.setTarih(LocalDateTime.now());
        
        // Yakıt verileri
        harcama.setKm(request.getKm());
        harcama.setYakitMiktar(request.getYakitMiktar());

        Harcama saved = harcamaRepository.save(harcama);
        return mapToResponse(saved);
    }

    public List<HarcamaResponse> getByPersonel(UUID personelId) {
        return harcamaRepository.findByPersonelId(personelId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<HarcamaResponse> getByIsId(UUID isId) {
        return harcamaRepository.findByIsId(isId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HarcamaResponse mapToResponse(Harcama h) {
        return HarcamaResponse.builder()
                .id(h.getId())
                .tutar(h.getTutar())
                .tarih(h.getTarih())
                .tur(h.getTur())
                .harcamaNotu(h.getHarcamaNotu())
                .belgeUrl(h.getBelgeUrl())
                .km(h.getKm())
                .yakitMiktar(h.getYakitMiktar())
                .build();
    }
}
