package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class JobResponse {
    private UUID id;
    private String firmaAdi;   // Denormalize alan
    private String yetkiliAdi; // Denormalize alan
    private String gorevAdi;
    private String gorevTuru;
    private String oncelik;
    private Integer durum;     // 0: Beklemede
    private LocalDateTime tarih;
    private LocalDateTime tamamlanmaTarihi;
}
