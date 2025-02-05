package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.service.BibleVerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private final BibleVerseService service;

    @Autowired
    public HomeController(BibleVerseService service) {
        this.service = service;
    }

    // Home page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Emotion Bible");
        return "index";
    }

    // Search page
    @GetMapping("/search")
    public String search(Model model) {
        return "search";
    }

    // Search for verses by emotion
    @GetMapping("/getVerse")
    public String getVerse(@RequestParam("emotion") String emotion, Model model) {
        try {
            Emotions emotionEnum = Emotions.valueOf(emotion.toUpperCase());
            BibleVerse verse = service.getRandomVerseByEmotion(emotionEnum); // Get a random verse based on the emotion
            model.addAttribute("verse", verse.getVerse()); // Add verse to model
        } catch (IllegalArgumentException e) {
            model.addAttribute("verse", "Invalid emotion");
        }
        return "search";

    }


}
