package com.example.notetaking.repositories;

import com.example.notetaking.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findByNotebookId(String notebookId);
    List<Note> findByUserId(String userId);
}