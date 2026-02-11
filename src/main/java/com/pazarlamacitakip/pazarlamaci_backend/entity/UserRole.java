package com.pazarlamacitakip.pazarlamaci_backend.entity;

/**
 * Sistemdeki kullanıcı rolleri.
 * 
 * DEVELOPER  – Süper Yönetici. Yeni şirket (Admin) oluşturur. Tüm verileri görür.
 * ADMIN      – Firma Sahibi. Developer tarafından oluşturulur. Kendi şirketinin personellerini yönetir.
 * PERSONEL   – Saha Çalışanı. Admin tarafından oluşturulur. Sahada veri toplar.
 */
public enum UserRole {
    DEVELOPER,
    ADMIN,
    PERSONEL
}
