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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    @Column(name = "adi", nullable = false)
    private String adi;
    
    @Column(name = "telefon")
    private String telefon;
    
    @Column(name = "adres")
    private String adres;
    
    @Column(name = "vergidairesi")
    private String vergidairesi;
    
    @Column(name = "vergino")
    private String vergino;
    
    @Column(name = "not")
    private String not;
    
    @Column(name = "sonziyarettarihi")
    private LocalDateTime sonZiyaretTarihi;
    
    @Column(name = "ziyaretsayisi")
    private Integer ziyaretSayisi;
    
    @Column(name = "aciklamalar")
    private String aciklamalar; // JSON string olarak saklanacak
}
