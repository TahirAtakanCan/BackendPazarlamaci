package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.FirmaSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.FirmaResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Firma;
import com.pazarlamacitakip.pazarlamaci_backend.repository.FirmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FirmaService {

    private final FirmaRepository firmaRepository;

    // Tüm Firmaları Getir
    public List<FirmaResponse> getAllFirmas() {
        return firmaRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ID ile Firma Getir
    public FirmaResponse getFirmaById(UUID id) {
        Firma firma = firmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Firma bulunamadı: " + id));
        return mapToResponse(firma);
    }

    // Firma Kaydet
    public FirmaResponse createFirma(FirmaSaveRequest request) {
        Firma firma = new Firma();
        firma.setAdi(request.getAdi());
        firma.setAdres(request.getAdres());
        firma.setTelefon(request.getTelefon());
        firma.setEmail(request.getEmail());
        firma.setSektor(request.getSektor());
        firma.setSehir(request.getSehir());
        firma.setIlce(request.getIlce());
        firma.setBakiye(request.getBakiye());
        firma.setAciklama(request.getAciklama());
        firma.setSirketkodu(request.getSirketkodu());
        firma.setAktifmi(true); // Varsayılan aktif

        Firma savedFirma = firmaRepository.save(firma);
        return mapToResponse(savedFirma);
    }

    // Firma Sil (Soft Delete - Aktifmi false yapıyoruz)
    public void deleteFirma(UUID id) {
        Firma firma = firmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Firma bulunamadı"));
        firma.setAktifmi(false); // Veriyi silme, pasife çek
        firmaRepository.save(firma);
    }

    // Yardımcı Metot: Entity -> DTO Çevirici
    private FirmaResponse mapToResponse(Firma firma) {
        return FirmaResponse.builder()
                .id(firma.getId())
                .adi(firma.getAdi())
                .adres(firma.getAdres())
                .telefon(firma.getTelefon())
                .email(firma.getEmail())
                .sektor(firma.getSektor())
                .sehir(firma.getSehir())
                .ilce(firma.getIlce())
                .bakiye(firma.getBakiye())
                .aciklama(firma.getAciklama())
                .aktifmi(firma.getAktifmi())
                .sirketkodu(firma.getSirketkodu())
                .build();
    }
}
