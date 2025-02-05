package com.example.emotions.controller;

import com.example.emotions.models.BibleVerse;
import com.example.emotions.models.Emotions;
import com.example.emotions.service.BibleVerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.RequestEntity.put;

@Controller
public class HomeController {
    private final BibleVerseService service;

    @Autowired
    public HomeController(BibleVerseService service) {
        this.service = service;
    }
    private static final Map<String, Emotions> SIMILAR_EMOTIONS = new HashMap<>() {{
        put("HAPPY", Emotions.JOY);
        put("CONTENT", Emotions.JOY);
        put("EXCITED", Emotions.JOY);
        put("SCARED", Emotions.FEAR);
        put("WORRIED", Emotions.ANXIOUS);
        put("NERVOUS", Emotions.ANXIOUS);
        put("DEPRESSED", Emotions.SADNESS);
        put("HEARTBROKEN", Emotions.SADNESS);
        put("LONELY", Emotions.ALONE);
        put("ISOLATED", Emotions.ALONE);
    }};

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
        try {
            String normalizedEmotion = emotion.toUpperCase();
            Emotions emotionEnum;
            if (isValidEmotion(normalizedEmotion)) {
                emotionEnum = Emotions.valueOf(normalizedEmotion);
            } else if (SIMILAR_EMOTIONS.containsKey(normalizedEmotion)) {
                emotionEnum = SIMILAR_EMOTIONS.get(normalizedEmotion);
                model.addAttribute("suggestion", "Did you mean: " + emotionEnum.name().toLowerCase() + "?");
            } else {
                model.addAttribute("verse", "Emotion not found. Try Joy, Sadness, Fear, Anxious, or Alone.");
                return "search";
            }

            BibleVerse verse = service.getRandomVerseByEmotion(emotionEnum);
            model.addAttribute("verse", verse.getVerse());
        } catch (Exception e) {
            model.addAttribute("verse", "Emotion not found. Try Joy, Sadness, Fear, Anxious, or Alone.");
        }
        return "search";
    }

    private boolean isValidEmotion(String emotion) {
        for (Emotions e : Emotions.values()) {
            if (e.name().equals(emotion)) {
                return true;
            }
        }
        return false;
    }
}



