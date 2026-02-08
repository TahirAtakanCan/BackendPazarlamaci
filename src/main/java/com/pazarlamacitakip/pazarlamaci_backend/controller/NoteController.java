package com.pazarlamacitakip.pazarlamaci_backend.controller;

import com.pazarlamacitakip.pazarlamaci_backend.dto.request.NoteSaveRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.request.NoteUpdateRequest;
import com.pazarlamacitakip.pazarlamaci_backend.dto.response.NoteResponse;
import com.pazarlamacitakip.pazarlamaci_backend.entity.Note;
import com.pazarlamacitakip.pazarlamaci_backend.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody NoteSaveRequest request) {
        return ResponseEntity.ok(noteService.createNote(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable UUID id, @RequestBody NoteUpdateRequest request) {
        return ResponseEntity.ok(noteService.updateNote(id, request));
    }

    @GetMapping("/firma/{firmaId}")
    public ResponseEntity<List<Note>> getByFirma(@PathVariable UUID firmaId) {
        return ResponseEntity.ok(noteService.getNotesByFirma(firmaId));
    }
}
