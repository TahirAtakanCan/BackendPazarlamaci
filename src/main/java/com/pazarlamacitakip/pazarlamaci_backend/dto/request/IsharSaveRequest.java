package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class IsharSaveRequest {
    private UUID isId;        // Hangi işe ait?
    private String gorevAdi;  // Yapılan işlem (Örn: Tahsilat)
    private String gorevAciklama;
    private String gorevNotu;
    private Integer durum;    // 1: Yapıldı
    
    // Finansal Veriler
    private Double tahsilTutari;
    private Double tahsilEdilenTutar;
    private String odemeTuru; // NAKİT, ÇEK
    
    private String belgeUrl; // Fotoğraf URL'i (String olarak gelecek)
}
