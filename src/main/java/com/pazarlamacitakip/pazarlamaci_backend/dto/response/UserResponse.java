package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String adi;
    private String email;
    private String telefon;
    private String role;
    private String bolge;
    private Boolean aktifmi;
    private String sirketkodu;
    private String yer;
    private String plaka;
}