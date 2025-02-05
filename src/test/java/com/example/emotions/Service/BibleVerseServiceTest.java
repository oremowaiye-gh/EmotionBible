package com.example.emotions.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.repository.BibleVerseRepository;
import com.example.emotions.service.BibleVerseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BibleVerseServiceTest {

    @Mock
    private BibleVerseRepository repository;

    @InjectMocks
    private BibleVerseService service;

    private BibleVerse verse1;
    private BibleVerse verse2;
    private BibleVerse verse3;

    @BeforeEach
    void setUp() {
        verse1 = new BibleVerse(1L, "Romans 14:17", "For the kingdom of God is not a matter of eating and drinking but of righteousness, peace and joy in the Holy Spirit", Emotions.JOY);
        verse2 = new BibleVerse(2L, "Isaiah 41:10", "Do not fear, for I am with you; do not be dismayed, for I am your God.", Emotions.FEAR);
        verse3 = new BibleVerse(3L, "Philippians 4:4", "Rejoice in the Lord always. I will say it again: Rejoice!", Emotions.JOY);
    }

    @Test
    void testGetVersesByEmotion() {
        when(repository.findByEmotion(Emotions.JOY)).thenReturn(List.of(verse1, verse3));

        List<BibleVerse> verses = service.getVersesByEmotion(Emotions.JOY);

        assertEquals(2, verses.size());

        assertTrue(verses.stream().anyMatch(v -> v.getReference().equals("Philippians 4:4")));
        assertTrue(verses.stream().anyMatch(v -> v.getReference().equals("Romans 14:17")));

        verify(repository, times(1)).findByEmotion(Emotions.JOY);
    }

    @Test
    void testGetVersesByEmotion_NoResults() {
        when(repository.findByEmotion(Emotions.SADNESS)).thenReturn(List.of());

        List<BibleVerse> verses = service.getVersesByEmotion(Emotions.SADNESS);

        assertTrue(verses.isEmpty());
        verify(repository, times(1)).findByEmotion(Emotions.SADNESS);
    }

    @Test
    void testGetRandomVerseByEmotion() {
        when(repository.findRandomByEmotion(Emotions.JOY)).thenReturn(List.of(verse1, verse3));

        BibleVerse verse = service.getRandomVerseByEmotion(Emotions.JOY);

        assertNotNull(verse);
        assertTrue(verse.getReference().equals("Philippians 4:4") || verse.getReference().equals("Romans 14:17"));

        verify(repository, times(1)).findRandomByEmotion(Emotions.JOY);
    }

}