package com.example.emotions.service;


import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.repository.BibleVerseRepository;

import java.util.List;

public class BibleVerseService {

    private BibleVerseRepository repository;

    public BibleVerseService(BibleVerseRepository repository) {
        this.repository = repository;
    }

    public List<BibleVerse> getAllVerses() {
        return repository.findAll();
    }

    public List<BibleVerse> getVersesByEmotion(Emotions emotion) {
        return repository.findByEmotion(emotion);
    }

    public BibleVerse addBibleVerse(BibleVerse bibleVerse) {
        return repository.save(bibleVerse);
    }

}
