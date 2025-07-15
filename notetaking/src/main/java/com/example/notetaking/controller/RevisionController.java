//package com.example.notetaking.controllers;
//
//import org.springframework.ai.chat.ChatClient;
//import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/revision")
//public class RevisionController {
//    @Autowired
//    private ChatClient chatClient;
//    @Autowired
//    private VectorStore vectorStore;
//
//    @PostMapping("/questions")
//    public String generateQuestions(@RequestParam String noteId, @RequestParam String questionType) {
//        // Fetch note content (simplified, extend with NoteRepository)
//        String noteContent = "Sample note content"; // Replace with actual note fetch
//        String prompt = "Generate " + questionType + " questions based on this content: " + noteContent;
//        return chatClient.call(prompt);
//    }
//}