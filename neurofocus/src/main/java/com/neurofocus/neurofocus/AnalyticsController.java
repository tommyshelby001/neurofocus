package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {

    @Autowired
    private FocusSessionRepository focusSessionRepository;

    // 🔥 ANALYTICS API
    @GetMapping("/analytics/{userId}")
    public Map<String, Object> getAnalytics(@PathVariable int userId) {

        List<FocusSession> sessions = focusSessionRepository.findByUserId(userId);

        Map<String, Object> result = new HashMap<>();

        if (sessions.isEmpty()) {
            result.put("message", "No data found");
            return result;
        }

        long totalFocus = 0;
        int totalDistractions = 0;
        long bestSession = 0;

        for (FocusSession s : sessions) {
            totalFocus += s.getDuration();
            totalDistractions += s.getDistractions();

            if (s.getDuration() > bestSession) {
                bestSession = s.getDuration();
            }
        }

        double avgFocus = (double) totalFocus / sessions.size();

        result.put("totalFocusMinutes", totalFocus);
        result.put("averageSession", avgFocus);
        result.put("bestSession", bestSession);
        result.put("totalDistractions", totalDistractions);

        return result;
    }
}