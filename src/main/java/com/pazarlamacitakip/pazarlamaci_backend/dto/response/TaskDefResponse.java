package com.pazarlamacitakip.pazarlamaci_backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class TaskDefResponse {
    private UUID id;
    private String adi;
    private String aciklama;
    private Boolean tutarGirisi;
}
