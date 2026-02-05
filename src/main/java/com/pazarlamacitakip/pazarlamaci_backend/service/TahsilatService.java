package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.TahsilatSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.TahsilatResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Tahsilat;
import com.pazarlamacitakip.pazarlamaci_backend.repository.TahsilatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TahsilatService {

    private final TahsilatRepository tahsilatRepository;

    public TahsilatResponse createTahsilat(TahsilatSaveRequest request) {
        Tahsilat tahsilat = new Tahsilat();
        tahsilat.setIsId(request.getIsId());
        tahsilat.setPersonelId(request.getPersonelId());
        tahsilat.setTediyeTutari(request.getTediyeTutari());
        tahsilat.setTahsilEdilenTutar(request.getTahsilEdilenTutar());
        tahsilat.setTarih(request.getTarih());
        tahsilat.setVadeTarihi(request.getVadeTarihi());

        Tahsilat saved = tahsilatRepository.save(tahsilat);
        return mapToResponse(saved);
    }

    public List<TahsilatResponse> getByPersonel(UUID personelId) {
        return tahsilatRepository.findByPersonelId(personelId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<TahsilatResponse> getAll() {
        return tahsilatRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TahsilatResponse mapToResponse(Tahsilat t) {
        return TahsilatResponse.builder()
                .id(t.getId())
                .isId(t.getIsId())
                .personelId(t.getPersonelId())
                .tediyeTutari(t.getTediyeTutari())
                .tahsilEdilenTutar(t.getTahsilEdilenTutar())
                .tarih(t.getTarih())
                .vadeTarihi(t.getVadeTarihi())
                .build();
    }
}
