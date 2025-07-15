package com.example.notetaking.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String notebookId;
    private String userId;
    private String title;
    private String content;
    private List<String> tags;
    private Date createdAt;

    public Note() {}

    public Note(String notebookId, String userId, String title, String content, List<String> tags) {
        this.notebookId = notebookId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.createdAt = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNotebookId() { return notebookId; }
    public void setNotebookId(String notebookId) { this.notebookId = notebookId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}