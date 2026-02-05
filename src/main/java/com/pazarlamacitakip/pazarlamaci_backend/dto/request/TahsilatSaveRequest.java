package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TahsilatSaveRequest {
    private UUID isId;        // Hangi iş sırasında alındı?
    private UUID personelId;  // Kim tahsil etti?
    private Double tediyeTutari;      // Ödenmesi gereken
    private Double tahsilEdilenTutar; // Ödenen
    
    // Vade Takibi (Çek/Senet için kritik)
    private LocalDateTime tarih;      // İşlem tarihi
    private LocalDateTime vadeTarihi; // Çek tarihi
}
