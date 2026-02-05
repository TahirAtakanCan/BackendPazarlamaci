package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TahsilatResponse {
    private UUID id;
    private UUID isId;
    private UUID personelId;
    private Double tediyeTutari;
    private Double tahsilEdilenTutar;
    private LocalDateTime tarih;
    private LocalDateTime vadeTarihi;
}
