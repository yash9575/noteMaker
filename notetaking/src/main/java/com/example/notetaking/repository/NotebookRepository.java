package com.example.notetaking.repositories;

import com.example.notetaking.models.Notebook;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotebookRepository extends MongoRepository<Notebook, String> {
    List<Notebook> findByUserId(String userId);
}