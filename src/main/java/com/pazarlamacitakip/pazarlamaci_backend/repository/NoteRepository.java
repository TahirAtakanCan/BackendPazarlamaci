package com.pazarlamacitakip.pazarlamaci_backend.repository;

import com.pazarlamacitakip.pazarlamaci_backend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    List<Note> findByFirmaId(UUID firmaId);
    List<Note> findByGorevId(UUID gorevId);
    List<Note> findByPersonelId(UUID personelId);
}
