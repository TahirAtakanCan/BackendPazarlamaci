package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class YetkiliResponse {
    private UUID id;
    private UUID firmaId;
    private String adi;
    private String soyadi;
    private String email;
    private String telefon;
    private String unvan;
    private String aciklama;
    private Boolean aktifmi;
}
