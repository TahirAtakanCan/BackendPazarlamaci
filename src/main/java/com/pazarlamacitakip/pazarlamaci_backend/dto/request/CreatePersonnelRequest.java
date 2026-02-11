package com.pazarlamacitakip.pazarlamaci_backend.dto.request;

import lombok.Data;

/**
 * Admin'in kendi şirketine yeni bir personel oluşturmak için gönderdiği istek.
 * Admin'in sirketkodu otomatik olarak personele kopyalanır.
 */
@Data
public class CreatePersonnelRequest {
    private String adi;
    private String email;
    private String sifre;
    private String telefon;
    private String bolge;
    private String yer;
    private String plaka;
}
