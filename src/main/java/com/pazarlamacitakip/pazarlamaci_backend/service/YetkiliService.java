package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.YetkiliSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.YetkiliResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Firma;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Yetkili;
import com.pazarlamacitakip.pazarlamaci_backend.repository.FirmaRepository;
import com.pazarlamacitakip.pazarlamaci_backend.repository.YetkiliRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class YetkiliService {

    private final YetkiliRepository yetkiliRepository;
    private final FirmaRepository firmaRepository;

    // Bir firmaya ait yetkilileri getir
    public List<YetkiliResponse> getYetkililerByFirmaId(UUID firmaId) {
        return yetkiliRepository.findByFirmaId(firmaId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Yetkili Kaydet
    public YetkiliResponse createYetkili(YetkiliSaveRequest request) {
        Firma firma = firmaRepository.findById(request.getFirmaId())
                .orElseThrow(() -> new RuntimeException("Firma bulunamadı!"));

        Yetkili yetkili = new Yetkili();
        yetkili.setFirma(firma); // İlişkiyi kurduk
        yetkili.setAdi(request.getAdi());
        yetkili.setSoyadi(request.getSoyadi());
        yetkili.setEmail(request.getEmail());
        yetkili.setTelefon(request.getTelefon());
        yetkili.setUnvan(request.getUnvan());
        yetkili.setAciklama(request.getAciklama());
        yetkili.setAktifmi(true);

        Yetkili savedYetkili = yetkiliRepository.save(yetkili);
        return mapToResponse(savedYetkili);
    }

    private YetkiliResponse mapToResponse(Yetkili yetkili) {
        return YetkiliResponse.builder()
                .id(yetkili.getId())
                .firmaId(yetkili.getFirma().getId())
                .adi(yetkili.getAdi())
                .soyadi(yetkili.getSoyadi())
                .email(yetkili.getEmail())
                .telefon(yetkili.getTelefon())
                .unvan(yetkili.getUnvan())
                .aciklama(yetkili.getAciklama())
                .aktifmi(yetkili.getAktifmi())
                .build();
    }
}
