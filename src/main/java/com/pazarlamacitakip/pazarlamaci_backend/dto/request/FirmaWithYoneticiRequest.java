package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

@Data
public class FirmaWithYoneticiRequest {
    // Firma bilgileri
    private String firmaAdi;
    private String firmaAdres;
    private String firmaTelefon;
    private String firmaEmail;
    private String firmaSektor;
    private String firmaSehir;
    private String firmaIlce;
    private String firmaSirketkodu;

    // Yönetici (User) bilgileri
    private String yoneticiAdi;
    private String yoneticiEmail;
    private String yoneticiSifre;
    private String yoneticiTelefon;
}
