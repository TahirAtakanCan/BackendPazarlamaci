package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class HarcamaSaveRequest {
    private UUID personelId;
    private Double tutar;
    private String tur; // YAKIT, YEMEK, KONAKLAMA
    private String harcamaNotu;
    private String belgeUrl;

    // Yakıt ise dolacak alanlar
    private Double km;
    private Double yakitMiktar;
}
