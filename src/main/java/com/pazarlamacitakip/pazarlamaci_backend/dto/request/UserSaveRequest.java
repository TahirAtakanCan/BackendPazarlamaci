package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data; // Bu import ŞART
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Getter'ları (getAdi, getEmail vb.) bu üretiyor
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {
    private String adi;
    private String sifre;
    private String telefon;
    private String email;
    private String aciklama;
    private String sirketkodu;
    private String plaka;
    private String yer;
    private String bolge;
    private Double aylikHedef;
    private Double haftalikHedef;
    private Integer hedefZiyaretSayisi;
}