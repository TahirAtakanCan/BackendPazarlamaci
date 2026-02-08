package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobUpdateRequest {
    private Integer durum;
    private LocalDateTime tamamlanmaTarihi;
}
