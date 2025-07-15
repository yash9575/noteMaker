package com.example.notetaking.controllers;

import com.example.notetaking.models.Notebook;
import com.example.notetaking.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
public class NotebookController {
    @Autowired
    private NotebookRepository notebookRepository;

    @GetMapping
    public List<Notebook> getNotebooks(@RequestParam String userId) {
        return notebookRepository.findByUserId(userId);
    }

    @PostMapping
    public Notebook createNotebook(@RequestBody Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notebook> updateNotebook(@PathVariable String id, @RequestBody Notebook notebook) {
        return notebookRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(notebook.getTitle());
                    return ResponseEntity.ok(notebookRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotebook(@PathVariable String id) {
        if (notebookRepository.existsById(id)) {
            notebookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
