package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findByPersonelId(UUID personelId); // Personelin işleri
}
