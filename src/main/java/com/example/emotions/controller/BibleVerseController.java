package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.repository.BibleVerseRepository;
import com.example.emotions.service.BibleVerseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verses")
public class BibleVerseController {

    private final BibleVerseService service;

    public BibleVerseController(BibleVerseService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public List<BibleVerse> searchByEmotion(@RequestParam String emotion) {
        return service.getVersesByEmotion(Emotions.valueOf(emotion.toUpperCase()));
    }
    @GetMapping("/random")
    public BibleVerse getRandomVerse(@RequestParam String emotion) {

        return service.getRandomVerseByEmotion(Emotions.valueOf(emotion.toUpperCase()));
    }
}
