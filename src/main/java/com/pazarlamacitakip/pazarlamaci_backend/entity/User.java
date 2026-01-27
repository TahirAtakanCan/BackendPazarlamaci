package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "adi", nullable = false)
    private String adi;

    @Column(name = "sifre", nullable = false)
    private String sifre;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "email")
    private String email;

    @Column(name = "adminmi")
    private Boolean adminmi = false;

    @Column(name = "aktifmi")
    private Boolean aktifmi = true;

    // --- Mobil & Web Ortak Alanlar ---
    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "aciklama2")
    private String aciklama2;

    @Column(name = "sirketkodu")
    private String sirketkodu;

    @Column(name = "yetki") // supervisor, manager vs.
    private String yetki;

    @Column(name = "bolge")
    private String bolge;

    @Column(name = "yer") // Lokasyon tanımı
    private String yer;

    // --- Araç Bilgileri ---
    @Column(name = "plaka")
    private String plaka;

    @Column(name = "km") // Aracın o anki kilometresi (String olarak saklanmış mobilde)
    private String km;

    // --- Hedef ve Performans Metrikleri ---
    @Column(name = "aylik_hedef")
    private Double aylikHedef;

    @Column(name = "haftalik_hedef")
    private Double haftalikHedef;

    @Column(name = "hedef_ziyaret_sayisi")
    private Integer hedefZiyaretSayisi;

    @Column(name = "gec_tahsilat")
    private Double gecTahsilat;

    @Column(name = "gec_tahsilat_guncelleme_tarihi")
    private LocalDateTime gecTahsilatGuncellemeTarihi;

    @Column(name = "son_giris_tarihi")
    private LocalDateTime sonGirisTarihi;
}