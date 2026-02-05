package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class YetkiliSaveRequest {
    private UUID firmaId; // Hangi firmaya bağlı?
    private String adi;
    private String soyadi;
    private String email;
    private String telefon;
    private String unvan; // Müdür, Şef vb.
    private String aciklama;
}
