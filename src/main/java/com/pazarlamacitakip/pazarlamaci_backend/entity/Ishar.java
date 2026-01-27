package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ishar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ishar {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "is_id", nullable = false)
    private UUID isId; // Job ID

    @Column(name = "gorev_adi")
    private String gorevAdi; // Örn: Tahsilat

    @Column(name = "aciklama")
    private String gorevAciklama;

    @Column(name = "notu")
    private String gorevNotu;

    @Column(name = "durum") // 0: Yapılmadı, 1: Yapıldı
    private Integer durum;

    @Column(name = "tarih")
    private LocalDateTime tarih;

    @Column(name = "duzenlenme_tarihi")
    private LocalDateTime duzenlenmeTarihi;

    // --- Finansal Veriler ---
    @Column(name = "tahsil_tutari")
    private Double tahsilTutari;

    @Column(name = "tum_tahsil_tutari")
    private Double tumTahsilTutari;

    @Column(name = "tahsil_edilen_tutar")
    private Double tahsilEdilenTutar;

    @Column(name = "odeme_turu") // NAKİT, HAVALE, ÇEK
    private String odemeTuru;

    @Column(name = "belge_url")
    private String belgeUrl;
}