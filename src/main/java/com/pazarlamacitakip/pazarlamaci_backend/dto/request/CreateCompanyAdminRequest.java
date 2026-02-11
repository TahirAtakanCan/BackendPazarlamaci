package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

/**
 * Developer'ın yeni bir şirket admini oluşturmak için gönderdiği istek.
 * Yeni bir UUID sirketkodu otomatik üretilir.
 */
@Data
public class CreateCompanyAdminRequest {
    private String adi;
    private String email;
    private String sifre;
    private String telefon;
}
