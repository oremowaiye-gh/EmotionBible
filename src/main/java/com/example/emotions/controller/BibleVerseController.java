package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.service.BibleVerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bible-verses")
@CrossOrigin("*")
public class BibleVerseController {

    private final BibleVerseService service;

    @Autowired
    public BibleVerseController(BibleVerseService service) {
        this.service = service;
    }

    @GetMapping
    public List<BibleVerse> getAllVerses() {
        return service.getAllVerses();
    }

    @GetMapping("/{emotion}")
    public List<BibleVerse> getVersesByEmotion(@PathVariable Emotions emotion) {
        return service.getVersesByEmotion(emotion);
    }

    @PostMapping
    public BibleVerse addBibleVerse(@RequestBody BibleVerse bibleVerse) {
        return service.addBibleVerse(bibleVerse);
    }
}