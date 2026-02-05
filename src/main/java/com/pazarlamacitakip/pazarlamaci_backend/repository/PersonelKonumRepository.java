package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.PersonelKonum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PersonelKonumRepository extends JpaRepository<PersonelKonum, UUID> {
    // Son konumları getirmek için tarihe göre sıralı liste gerekebilir
    List<PersonelKonum> findByPersonelIdOrderByTimestampDesc(UUID personelId);
}
