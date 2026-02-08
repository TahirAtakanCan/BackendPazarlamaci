package com.pazarlamacitakip.pazarlamaci_backend.service;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.NoteSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.NoteUpdateRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.NoteResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Note;
import com.pazarlamacitakip.pazarlamaci_backend.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(NoteSaveRequest request) {
        Note note = new Note();
        note.setPersonelId(request.getPersonelId());
        note.setPersonelAdi(request.getPersonelAdi());
        note.setFirmaId(request.getFirmaId());
        note.setGorevId(request.getGorevId());
        note.setNotMetni(request.getNotMetni());
        note.setIcNot(request.getIcNot());
        note.setOlusturulmaTarihi(LocalDateTime.now());

        return noteRepository.save(note);
    }

    public List<Note> getNotesByFirma(UUID firmaId) {
        return noteRepository.findByFirmaId(firmaId);
    }
    
    public List<Note> getNotesByJob(UUID jobId) {
        return noteRepository.findByGorevId(jobId);
    }

    public NoteResponse updateNote(UUID id, NoteUpdateRequest request) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı: " + id));

        if (request.getNotMetni() != null) {
            note.setNotMetni(request.getNotMetni());
        }

        Note updated = noteRepository.save(note);
        return mapToResponse(updated);
    }

    private NoteResponse mapToResponse(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .gorevId(note.getGorevId())
                .isharId(note.getIsharId())
                .firmaId(note.getFirmaId())
                .personelId(note.getPersonelId())
                .personelAdi(note.getPersonelAdi())
                .personelTipi(note.getPersonelTipi())
                .gorevAdi(note.getGorevAdi())
                .notMetni(note.getNotMetni())
                .icNot(note.getIcNot())
                .olusturulmaTarihi(note.getOlusturulmaTarihi())
                .build();
    }
}
