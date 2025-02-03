package com.example.emotions.repository;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibleVerseRepository extends JpaRepository<BibleVerse, String> {
    List<BibleVerse> findByEmotion(Emotions emotion);

    }


