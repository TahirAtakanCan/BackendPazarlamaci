package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Harcama;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface HarcamaRepository extends JpaRepository<Harcama, UUID> {
    List<Harcama> findByPersonelId(UUID personelId);
    List<Harcama> findByIsId(UUID isId);
}
