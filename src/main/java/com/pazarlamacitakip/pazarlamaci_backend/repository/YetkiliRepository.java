package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Yetkili;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface YetkiliRepository extends JpaRepository<Yetkili, UUID> {
    List<Yetkili> findByFirmaId(UUID firmaId);

    // Şirket koduna göre o şirkete ait tüm firmaların tüm yetkililerini getir
    List<Yetkili> findByFirma_Sirketkodu(String sirketkodu);
}