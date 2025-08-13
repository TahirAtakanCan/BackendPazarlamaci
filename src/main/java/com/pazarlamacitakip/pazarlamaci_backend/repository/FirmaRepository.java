package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FirmaRepository extends JpaRepository<Firma, UUID> {
}
