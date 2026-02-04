package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class FirmaResponse {
    private UUID id;
    private String adi;
    private String adres;
    private String telefon;
    private String email;
    private String sektor;
    private String sehir;
    private String ilce;
    private Double bakiye;
    private String aciklama;
    private Boolean aktifmi;
    private String sirketkodu;
}
