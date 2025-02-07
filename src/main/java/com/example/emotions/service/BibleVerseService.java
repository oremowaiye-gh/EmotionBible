package com.example.emotions.service;


import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.repository.BibleVerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibleVerseService {

    private final BibleVerseRepository repository;

    @Autowired
    public BibleVerseService(BibleVerseRepository repository) {
        this.repository = repository;
    }


    public List<BibleVerse> getVersesByEmotion(Emotions emotion) {
        return repository.findByEmotion(emotion);
    }


    public BibleVerse getRandomVerseByEmotion(Emotions emotion) {
        List<BibleVerse> verses = repository.findRandomByEmotion(emotion);
        return verses.get(0);
    }
    public BibleVerse saveToFavourites(BibleVerse bibleVerse) {
        bibleVerse.setFavourite(1);
        return repository.save(bibleVerse);
    }
    public void removeFromFavourites(Long id) {
        BibleVerse verse = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid verse Id:" + id));
        verse.setFavourite(0);
        repository.save(verse);
    }




    public List<BibleVerse> getFavoriteVerses() {
        return repository.findByFavourite(1);
    }




}
