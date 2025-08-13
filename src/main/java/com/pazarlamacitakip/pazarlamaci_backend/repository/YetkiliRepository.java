package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Yetkili;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface YetkiliRepository extends JpaRepository<Yetkili, UUID> {
}
