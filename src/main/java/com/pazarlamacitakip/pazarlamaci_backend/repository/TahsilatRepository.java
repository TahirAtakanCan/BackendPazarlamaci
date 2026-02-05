package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Tahsilat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TahsilatRepository extends JpaRepository<Tahsilat, UUID> {
    List<Tahsilat> findByPersonelId(UUID personelId);
    List<Tahsilat> findByIsId(UUID isId);
}
