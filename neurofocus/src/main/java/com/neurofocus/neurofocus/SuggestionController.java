package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuggestionController {

    @Autowired
    private FocusSessionRepository focusRepo;

    @GetMapping("/focus-advice/{userId}")
    public String getAdvice(@PathVariable int userId) {

        List<FocusSession> sessions = focusRepo.findByUserId(userId);

        if (sessions.isEmpty()) {
            return "Start focusing first 💡";
        }

        long totalFocus = 0;
        int totalDistractions = 0;

        for (FocusSession s : sessions) {
            totalFocus += s.getDuration();
            totalDistractions += s.getDistractions();
        }

        // 🔥 LOGIC
        if (totalFocus < 60) {
            return "Your focus is low 😴 Try Pomodoro method!";
        }

        if (totalDistractions > 5) {
            return "Too many distractions ⚠️ Turn off notifications!";
        }

        if (totalFocus > 120) {
            return "Excellent focus 🚀 Keep it up!";
        }

        return "Good job 👍 Stay consistent!";
    }
}