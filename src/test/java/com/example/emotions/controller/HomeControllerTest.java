package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.service.BibleVerseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @Mock
    private BibleVerseService service;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHome() {
        String viewName = controller.home(model);
        assertEquals("index", viewName);
    }

    @Test
    void testGetVerse_ValidEmotion() {
        BibleVerse verse = new BibleVerse();
        verse.setVerse("Test Verse");
        verse.setReference("Test Reference");
        when(service.getRandomVerseByEmotion(any(Emotions.class))).thenReturn(verse);

        String viewName = controller.getVerse("happy", model);

        verify(model).addAttribute("emotion", "joy");
        verify(model).addAttribute("verse", "Test Verse");
        verify(model).addAttribute("reference", "Test Reference");
        assertEquals("verseResult", viewName);
    }

    @Test
    void testGetVerse_InvalidEmotion() {
        String viewName = controller.getVerse("unknown", model);

        verify(model).addAttribute("verse", "Emotion not found. Try Joy, Sadness, Fear, Anxious, or Alone.");
        assertEquals("search", viewName);
    }

    @Test
    void testGetFavouriteVerses() {
        List<BibleVerse> favourites = Collections.singletonList(new BibleVerse());
        when(service.getFavoriteVerses()).thenReturn(favourites);

        String viewName = controller.getFavouriteVerses(model);

        verify(model).addAttribute("favourites", favourites);
        assertEquals("favourites", viewName);
    }

    @Test
    void testRemoveFavouriteVerse() {
        controller.removeFromFavourites(1L);
        verify(service).removeFromFavourites(1L);
    }
}