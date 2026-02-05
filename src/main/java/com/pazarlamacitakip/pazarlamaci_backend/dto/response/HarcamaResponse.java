package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class HarcamaResponse {
    private UUID id;
    private Double tutar;
    private LocalDateTime tarih;
    private String tur;
    private String harcamaNotu;
    private String belgeUrl;
    private Double km;
    private Double yakitMiktar;
}
