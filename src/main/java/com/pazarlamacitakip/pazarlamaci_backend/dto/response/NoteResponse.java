package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class NoteResponse {
    private UUID id;
    private UUID gorevId;
    private UUID isharId;
    private UUID firmaId;
    private UUID personelId;
    private String personelAdi;
    private String personelTipi;
    private String gorevAdi;
    private String notMetni;
    private Boolean icNot;
    private LocalDateTime olusturulmaTarihi;
}
