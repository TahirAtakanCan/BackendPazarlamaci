package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "yetkili")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Yetkili {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmaid", nullable = false)
    private Firma firma;
    
    @Column(name = "adi", nullable = false)
    private String adi;
    
    @Column(name = "soyadi", nullable = false)
    private String soyadi;
    
    @Column(name = "telefon")
    private String telefon;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "unvan")
    private String unvan;
    
    @Column(name = "aciklama")
    private String aciklama;
    
    @Column(name = "aktifmi")
    private Boolean aktifmi = true;
}
