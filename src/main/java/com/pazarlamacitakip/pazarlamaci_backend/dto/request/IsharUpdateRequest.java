package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

@Data
public class IsharUpdateRequest {
    private Integer durum;
    private Double tahsilTutari;
    private String gorevNotu;
}
