package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class NoteSaveRequest {
    private UUID personelId;
    private String personelAdi;
    
    // Bağlı olduğu yerler (Sadece biri dolu olabilir)
    private UUID firmaId;
    private UUID gorevId;
    
    private String notMetni;
    private Boolean icNot; // Sadece admin görsün mü?
}
