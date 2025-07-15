package com.example.notetaking.controllers;

import com.example.notetaking.models.Notebook;
import com.example.notetaking.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
public class NotebookController {
    @Autowired
    private NotebookRepository notebookRepository;

    @GetMapping
    public List<Notebook> getNotebooks() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return notebookRepository.findByUserId(userId);
    }

    @PostMapping
    public Notebook createNotebook(@RequestBody Notebook notebook) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        notebook.setUserId(userId);
        return notebookRepository.save(notebook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notebook> updateNotebook(@PathVariable String id, @RequestBody Notebook notebook) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return notebookRepository.findById(id)
                .filter(n -> n.getUserId().equals(userId))
                .map(existing -> {
                    existing.setTitle(notebook.getTitle());
                    return ResponseEntity.ok(notebookRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotebook(@PathVariable String id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (notebookRepository.findById(id).map(n -> n.getUserId().equals(userId)).orElse(false)) {
            notebookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}