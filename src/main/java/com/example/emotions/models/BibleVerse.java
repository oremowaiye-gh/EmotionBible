package com.example.emotions.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bible_verse")

public class BibleVerse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Emotions emotion;

    @Column(nullable = false)
    private String verse;

    public BibleVerse() {
    }

    public BibleVerse(Emotions emotion, String verse) {
        this.emotion = emotion;
        this.verse = verse;
    }

    public Emotions getEmotion() {
        return emotion;
    }
    public void setEmotion(Emotions emotion) {
        this.emotion = emotion;
    }

    public String getVerse() {
        return verse;
    }
    public void setVerse(String verse) {
        this.verse = verse;
    }
}
