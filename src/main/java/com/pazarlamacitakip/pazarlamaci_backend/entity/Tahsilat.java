package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tahsilat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tahsilat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "is_id")
    private UUID isId;

    @Column(name = "personel_id")
    private UUID personelId;

    @Column(name = "tarih")
    private LocalDateTime tarih;

    @Column(name = "vade_tarihi")
    private LocalDateTime vadeTarihi;

    @Column(name = "tediye_tutari")
    private Double tediyeTutari;

    @Column(name = "tahsil_edilen_tutar")
    private Double tahsilEdilenTutar;
}