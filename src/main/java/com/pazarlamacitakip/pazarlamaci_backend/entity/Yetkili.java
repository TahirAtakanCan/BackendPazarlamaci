package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    
    @Column(name = "firmaid", nullable = false)
    private UUID firmaid;
    
    @Column(name = "adi", nullable = false)
    private String adi;
    
    @Column(name = "soyadi", nullable = false)
    private String soyadi;
    
    @Column(name = "telefon")
    private String telefon;
    
    @Column(name = "email")
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firmaid", insertable = false, updatable = false)
    private Firma firma;
}
