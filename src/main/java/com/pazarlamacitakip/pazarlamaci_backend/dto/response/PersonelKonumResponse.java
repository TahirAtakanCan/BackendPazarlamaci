package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PersonelKonumResponse {
    private UUID personelId;
    private String firstName;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
