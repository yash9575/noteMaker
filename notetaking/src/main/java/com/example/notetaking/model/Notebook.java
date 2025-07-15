package com.example.notetaking.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notebooks")
public class Notebook {
    @Id
    private String id;
    private String userId;
    private String title;

    public Notebook() {}

    public Notebook(String userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}