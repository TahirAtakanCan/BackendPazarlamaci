package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Bağlı olduğu potansiyel yerler (Nullable)
    @Column(name = "gorev_id")
    private UUID gorevId;

    @Column(name = "ishar_id")
    private UUID isharId;

    @Column(name = "firma_id")
    private UUID firmaId;

    @Column(name = "personel_id")
    private UUID personelId;

    // Denormalize İsimler (Log amaçlı)
    @Column(name = "personel_adi")
    private String personelAdi;

    @Column(name = "personel_tipi")
    private String personelTipi;

    @Column(name = "gorev_adi")
    private String gorevAdi;

    @Column(name = "not_metni", columnDefinition = "TEXT")
    private String notMetni;

    @Column(name = "ic_not") // Sadece yönetici görür
    private Boolean icNot = false;

    @Column(name = "olusturulma_tarihi")
    private LocalDateTime olusturulmaTarihi;
}