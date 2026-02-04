package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

@Data
public class FirmaSaveRequest {
    private String adi;
    private String adres;
    private String telefon;
    private String email;
    private String sektor;
    private String sehir;
    private String ilce;
    private Double bakiye;
    private String aciklama;
    private String sirketkodu; // Firmayı hangi bayi/şirket yönetiyor
}
