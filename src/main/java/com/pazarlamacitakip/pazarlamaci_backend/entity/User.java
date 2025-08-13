package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "adi")
    private String adi;
    
    @Column(name = "sifre")
    private String sifre;
    
    @Column(name = "telefon")
    private String telefon;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "aciklama")
    private String aciklama;
    
    @Column(name = "aciklama2")
    private String aciklama2;
    
    @Column(name = "adminmi")
    private Boolean adminmi = false;
    
    @Column(name = "aktifmi")
    private Boolean aktifmi = true;
    
    @Column(name = "sirketkodu")
    private String sirketkodu;
    
    @Column(name = "plaka")
    private String plaka;
    
    @Column(name = "km")
    private Double km;
    
    @Column(name = "yer")
    private String yer;
    
    @Column(name = "yetki")
    private String yetki;
    
    @Column(name = "aylik_hedef")
    private Double aylikHedef;
    
    @Column(name = "haftalik_hedef")
    private Double haftalikHedef;
    
    @Column(name = "hedef_ziyaret_sayisi")
    private Integer hedefZiyaretSayisi;
    
    @Column(name = "bolge")
    private String bolge;
    
    @Column(name = "son_giris_tarihi")
    private LocalDateTime sonGirisTarihi;
    
    @Column(name = "gec_tahsilat")
    private Double gecTahsilat;
    
    @Column(name = "gec_tahsilat_guncelleme_tarihi")
    private LocalDateTime gecTahsilatGuncellemeTarihi;
}
