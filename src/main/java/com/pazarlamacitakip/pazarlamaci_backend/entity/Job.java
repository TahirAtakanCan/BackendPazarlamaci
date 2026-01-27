package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // İlişkiler (Foreign Keys)
    @Column(name = "personel_id")
    private UUID personelId;

    @Column(name = "firma_id")
    private UUID firmaId;

    @Column(name = "yetkili_id")
    private UUID yetkiliId;

    // --- Denormalize Alanlar (Snapshot - Log Mantığı) ---
    // Firma adı değişse bile bu iş kaydındaki eski ad kalsın diye.
    @Column(name = "firma_adi")
    private String firmaAdi;

    @Column(name = "yetkili_adi")
    private String yetkiliAdi;

    // --- İş Detayları ---
    @Column(name = "gorev_adi")
    private String gorevAdi;

    @Column(name = "gorev_turu") // TaskDef tablosundaki tür
    private String gorevTuru;

    @Column(name = "oncelik") // DÜŞÜK, NORMAL, YÜKSEK
    private String oncelik;

    @Column(name = "durum") // 0: Beklemede, 1: Tamamlandı, 2: İptal
    private Integer durum;

    // --- Zamanlama ve Metrikler ---
    @Column(name = "tarih")
    private LocalDateTime tarih;

    @Column(name = "ilk_tarih") // Atama tarihi
    private LocalDateTime ilkTarih;

    @Column(name = "tamamlanma_tarihi")
    private LocalDateTime tamamlanmaTarihi;

    @Column(name = "tahmini_sure") // Saat cinsinden
    private Double tahminiSure;

    @Column(name = "gerceklesen_sure") // Saat cinsinden
    private Double gerceklesenSure;

    @Column(name = "gorev_zamaninda_mi") // 0: Hayır, 1: Evet
    private Integer gorevZamanindaMi;

    // JPA İlişkileri (Opsiyonel - İhtiyaç olursa açılır, şimdilik ID tutuyoruz)
    // @ManyToOne ...
}