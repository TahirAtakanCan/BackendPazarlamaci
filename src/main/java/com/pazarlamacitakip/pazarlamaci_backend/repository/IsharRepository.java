package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Ishar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface IsharRepository extends JpaRepository<Ishar, UUID> {
    List<Ishar> findByIsId(UUID isId); // Bir işe ait hareketler
}
