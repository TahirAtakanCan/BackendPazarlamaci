package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class JobSaveRequest {
    private UUID personelId; // İşi yapacak kişi
    private UUID firmaId;    // Müşteri
    private UUID yetkiliId;  // Görüşülen kişi (Opsiyonel olabilir)
    
    private String gorevAdi;
    private String gorevTuru; // Ziyaret, Tahsilat vs.
    private String oncelik;   // DÜŞÜK, NORMAL, YÜKSEK
    private LocalDateTime tarih; // Planlanan tarih
    private Double tahminiSure;
}
