package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FocusController {

    @Autowired
    private FocusSessionRepository focusSessionRepository;

    // ==============================
    // 🔥 START FOCUS SESSION
    // ==============================
    @PostMapping("/start-focus/{userId}")
    public FocusSession startFocus(@PathVariable int userId) {

        FocusSession session = new FocusSession();
        session.setUserId(userId);
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(null);
        session.setDuration(0);
        session.setDistractions(0);

        return focusSessionRepository.save(session);
    }

    // ==============================
    // 🔥 END FOCUS SESSION
    // ==============================
    @PostMapping("/end-focus/{id}")
    public FocusSession endFocus(@PathVariable int id) {

        FocusSession session = focusSessionRepository.findById(id).orElse(null);

        if (session == null) return null;

        session.setEndTime(LocalDateTime.now());

        long minutes = Duration.between(
                session.getStartTime(),
                session.getEndTime()
        ).toMinutes();

        session.setDuration(minutes);

        return focusSessionRepository.save(session);
    }

    // ==============================
    // 🔥 ADD DISTRACTION
    // ==============================
    @PostMapping("/add-distraction/{id}")
    public FocusSession addDistraction(@PathVariable int id) {

        FocusSession session = focusSessionRepository.findById(id).orElse(null);

        if (session == null) return null;

        session.setDistractions(session.getDistractions() + 1);

        return focusSessionRepository.save(session);
    }

    // ==============================
    // 🔥 GET ALL SESSIONS
    // ==============================
    @GetMapping("/focus-sessions/{userId}")
    public List<FocusSession> getSessions(@PathVariable int userId) {
        return focusSessionRepository.findByUserId(userId);
    }

    // ==============================
    // 🔥 PRODUCTIVITY SCORE (MAIN FEATURE)
    // ==============================
    @GetMapping("/productivity-score/{userId}")
    public double getProductivityScore(@PathVariable int userId) {

        List<FocusSession> sessions = focusSessionRepository.findByUserId(userId);

        if (sessions.isEmpty()) return 0;

        long totalFocusTime = 0;
        int totalDistractions = 0;

        for (FocusSession s : sessions) {
            totalFocusTime += s.getDuration();
            totalDistractions += s.getDistractions();
        }

        if (totalFocusTime == 0) return 0;

        double score = (double) totalFocusTime /
                (totalFocusTime + (totalDistractions * 2)) * 100;

        // round to 2 decimal
        return Math.round(score * 100.0) / 100.0;
    }
}