package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.repository.BibleVerseRepository;
import com.example.emotions.service.BibleVerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final BibleVerseService service;
    private final BibleVerseRepository repository;

    @Autowired
    public HomeController(BibleVerseService service, BibleVerseRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    private static final Map<String, Emotions> SIMILAR_EMOTIONS = new HashMap<>();

    static {
        SIMILAR_EMOTIONS.put("HAPPY", Emotions.JOY);
        SIMILAR_EMOTIONS.put("SAD", Emotions.SADNESS);
        SIMILAR_EMOTIONS.put("AFRAID", Emotions.FEAR);
        SIMILAR_EMOTIONS.put("WORRIED", Emotions.ANXIOUS);
        SIMILAR_EMOTIONS.put("LONELY", Emotions.ALONE);
        SIMILAR_EMOTIONS.put("DEPRESSED", Emotions.SADNESS);
        SIMILAR_EMOTIONS.put("SCARED", Emotions.FEAR);
        SIMILAR_EMOTIONS.put("NERVOUS", Emotions.ANXIOUS);
        SIMILAR_EMOTIONS.put("ISOLATED", Emotions.ALONE);
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Emotion Bible");
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model) {
        return "search";
    }

    @GetMapping("/getVerse")
    public String getVerse(@RequestParam("emotion") String emotion, Model model) {

            String normalizedEmotion = emotion.toUpperCase();
            Emotions emotionEnum;

            if (isValidEmotion(normalizedEmotion)) {
                emotionEnum = Emotions.valueOf(normalizedEmotion);
            } else if (SIMILAR_EMOTIONS.containsKey(normalizedEmotion)) {
                emotionEnum = SIMILAR_EMOTIONS.get(normalizedEmotion);
            } else {
                model.addAttribute("verse", "Emotion not found. Try Joy, Sadness, Fear, Anxious, or Alone.");
                return "search";
            }

            BibleVerse verse = service.getRandomVerseByEmotion(emotionEnum);
            model.addAttribute("emotion", emotionEnum.name().toLowerCase());
            model.addAttribute("verse", verse.getVerse());
            model.addAttribute("reference", verse.getReference());
            model.addAttribute("verseId",verse.getId());

            return "verseResult";
        }



    private boolean isValidEmotion(String emotion) {
        for (Emotions e : Emotions.values()) {
            if (e.name().equals(emotion)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/searchByEmotion")
    public List<BibleVerse> searchByEmotion(@RequestParam String emotion) {
        return service.getVersesByEmotion(Emotions.valueOf(emotion.toUpperCase()));
    }

    @GetMapping("/random")
    public BibleVerse getRandomVerse(@RequestParam String emotion) {
        return service.getRandomVerseByEmotion(Emotions.valueOf(emotion.toUpperCase()));
    }

    @PostMapping("/favourites/add")
    public String addToFavorites(@RequestParam Long verseId) {
        BibleVerse bibleVerse = repository.findById(verseId).get();
        service.saveToFavourites(bibleVerse);
        return "redirect:/favourites";
    }

    @GetMapping("/favourites")
    public String getFavouriteVerses(Model model) {
        List<BibleVerse> favourites = service.getFavoriteVerses();
        model.addAttribute("favourites", favourites);
        return "favourites";
    }
    @PostMapping("/favourites/remove")
    public String removeFromFavorites(@RequestParam Long verseId) {
        service.removeFromFavourites(verseId);
        return "redirect:/favourites";
    }

}