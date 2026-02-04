package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String adi;
    private String email;
    private String sifre;
    private String telefon;
    private String sirketkodu;
}
