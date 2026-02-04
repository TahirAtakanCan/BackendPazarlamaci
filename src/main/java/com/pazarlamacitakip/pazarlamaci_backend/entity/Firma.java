package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "firma")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Firma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "adi", nullable = false)
    private String adi;
    
    @Column(name = "telefon")
    private String telefon;
    
    @Column(name = "adres")
    private String adres;

    @Column(name = "email")
    private String email;

    @Column(name = "sektor")
    private String sektor;

    @Column(name = "sehir")
    private String sehir;

    @Column(name = "ilce")
    private String ilce;

    @Column(name = "bakiye")
    private Double bakiye;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "sirketkodu")
    private String sirketkodu;

    @Column(name = "aktifmi")
    private Boolean aktifmi = true;
    
    @Column(name = "vergidairesi")
    private String vergidairesi;
    
    @Column(name = "vergino")
    private String vergino;
    
    @Column(name = "\"not\"")
    private String not;
    
    @Column(name = "sonziyarettarihi")
    private LocalDateTime sonZiyaretTarihi;
    
    @Column(name = "ziyaretsayisi")
    private Integer ziyaretSayisi;
    
    @Column(name = "aciklamalar")
    private String aciklamalar; // JSON string olarak saklanacak
}
