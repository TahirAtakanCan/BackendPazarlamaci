package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

@Data
public class TaskDefSaveRequest {
    private String adi;         // Örn: Rutin Ziyaret
    private String aciklama;
    private Boolean tutarGirisi; // Bu görevde para girişi olacak mı?
}
