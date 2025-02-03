package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.repository.BibleVerseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verses")
public class BibleVerseController {

    private final BibleVerseRepository bibleVerseRepository;

    public BibleVerseController(BibleVerseRepository bibleVerseRepository) {
        this.bibleVerseRepository = bibleVerseRepository;
    }

    @GetMapping("/search")
    public List<BibleVerse> searchByEmotion(@RequestParam String emotion) {
        return bibleVerseRepository.findByEmotion(Emotions.valueOf(emotion.toUpperCase()));
    }
}
