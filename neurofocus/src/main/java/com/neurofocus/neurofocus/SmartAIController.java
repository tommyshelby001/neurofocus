package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SmartAIController {

    @Autowired
    private FocusSessionRepository focusRepo;

    @GetMapping("/smart-advice/{userId}")
    public String getSmartAdvice(@PathVariable int userId) {

        List<FocusSession> sessions = focusRepo.findByUserId(userId);

        if (sessions.isEmpty()) return "Start focusing first 🚀";

        long total = 0;
        int distractions = 0;

        for (FocusSession s : sessions) {
            total += s.getDuration();
            distractions += s.getDistractions();
        }

        long avg = total / sessions.size();

        if (avg < 10) return "❌ Very low focus — increase study time";
        if (avg < 25) return "⚠️ Moderate — try deep work sessions";
        if (distractions > 5) return "📵 Too many distractions — stay focused";

        return "🔥 Excellent focus — keep going!";
    }
}