package com.example.emotions.repository;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibleVerseRepository extends CrudRepository<BibleVerse, Long> {
    List<BibleVerse> findByEmotion(Emotions emotion);

    @Query("SELECT b FROM BibleVerse b WHERE b.emotion = :emotion ORDER BY function('RANDOM')")
    List<BibleVerse> findRandomByEmotion(Emotions emotion);

    }


