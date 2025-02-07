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

//    public List<BibleVerse> getAllVerses() {
//        return (List<BibleVerse>) repository.findAll();
//    }

    public List<BibleVerse> getVersesByEmotion(Emotions emotion) {
        return repository.findByEmotion(emotion);
    }

//    public BibleVerse addBibleVerse(BibleVerse bibleVerse) {
//        return repository.save(bibleVerse);
//    }
    public BibleVerse getRandomVerseByEmotion(Emotions emotion) {
        List<BibleVerse> verses = repository.findRandomByEmotion(emotion);
        return verses.get(0);
    }
    public BibleVerse saveToFavourites(BibleVerse bibleVerse) {
        bibleVerse.setFavourite(1);
        return repository.save(bibleVerse);
    }




    public List<BibleVerse> getFavoriteVerses() {
        return repository.findByFavourite(1);
    }


}
