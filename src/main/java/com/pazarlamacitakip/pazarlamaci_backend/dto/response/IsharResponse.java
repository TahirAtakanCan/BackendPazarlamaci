package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class IsharResponse {
    private UUID id;
    private UUID isId;
    private String gorevAdi;
    private String gorevAciklama;
    private String gorevNotu;
    private Integer durum;
    private LocalDateTime tarih;
    
    private Double tahsilTutari;
    private Double tahsilEdilenTutar;
    private String odemeTuru;
    private String belgeUrl;
}
