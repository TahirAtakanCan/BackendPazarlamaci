package com.pazarlamacitakip.pazarlamaci_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "task_def") // 'tasks' rezerve kelime olabilir, task_def daha güvenli
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDef {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "adi")
    private String adi; // Görev Türü Adı

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "tutar_girisi_var_mi") // Boolean
    private Boolean tutarGirisi;
}