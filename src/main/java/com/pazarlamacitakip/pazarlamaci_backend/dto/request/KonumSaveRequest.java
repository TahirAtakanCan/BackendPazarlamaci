package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class KonumSaveRequest {
    private UUID personelId;
    private String firstName; // Log amaçlı isim
    private Double latitude;
    private Double longitude;
}
