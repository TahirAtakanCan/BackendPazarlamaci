package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthResponse {
    private String token;
    private UUID userId;
    private String adi;
    private String email;
    private String sirketkodu;
    private Boolean adminmi;
    private String yetki;
}
