package com.example.emotions.models;

import jakarta.persistence.*;
import jakarta.persistence.Version;


@Entity
@Table(name = "bible_verse")

public class BibleVerse {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Emotions emotion;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String verse;

    @Column(nullable = false)
    private int favourite;


    public BibleVerse() {
    }


    public BibleVerse(Long id, String reference, String verse, Emotions emotion) {
        this.id = id;
        this.reference = reference;
        this.verse = verse;
        this.emotion = emotion;
    }

    public Emotions getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotions emotion) {
        this.emotion = emotion;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }
    public void setFavourite(int favourite) {

        this.favourite = favourite;
    }

    public int isFavourite() {
        return favourite;
    }
}
