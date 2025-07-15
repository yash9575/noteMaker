package com.example.notetaking.controllers;

import com.example.notetaking.models.Note;
import com.example.notetaking.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping
    public List<Note> getNotes(@RequestParam String notebookId) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return noteRepository.findByNotebookId(notebookId).stream()
                .filter(note -> note.getUserId().equals(userId))
                .toList();
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        note.setUserId(userId);
        return noteRepository.save(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note note) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return noteRepository.findById(id)
                .filter(n -> n.getUserId().equals(userId))
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
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (noteRepository.findById(id).map(n -> n.getUserId().equals(userId)).orElse(false)) {
            noteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}