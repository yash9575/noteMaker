package com.example.notetaking.controllers;

import com.example.notetaking.models.Note;
import com.example.notetaking.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping
    public List<Note> getNotes(@RequestParam String notebookId) {
        return noteRepository.findByNotebookId(notebookId);
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note note) {
        return noteRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(note.getTitle());
                    existing.setContent(note.getContent());
                    existing.setTags(note.getTags());
                    return ResponseEntity.ok(noteRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}