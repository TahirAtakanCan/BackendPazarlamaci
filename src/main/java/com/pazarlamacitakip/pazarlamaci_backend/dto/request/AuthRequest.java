package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String sifre;
}
