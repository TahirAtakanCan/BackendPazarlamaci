package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "harcama")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Harcama {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "personel_id")
    private UUID personelId;

    @Column(name = "tutar")
    private Double tutar;

    @Column(name = "tarih")
    private LocalDateTime tarih;

    @Column(name = "tur") // YAKIT, YEMEK, KONAKLAMA
    private String tur;

    @Column(name = "aciklama")
    private String harcamaNotu;

    @Column(name = "belge_url")
    private String belgeUrl;

    // --- Yakıt Takibi ---
    @Column(name = "km") // Harcama anındaki KM
    private Double km;

    @Column(name = "yakit_miktar") // Litre
    private Double yakitMiktar;
}