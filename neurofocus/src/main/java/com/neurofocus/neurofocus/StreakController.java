package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class StreakController {

    @Autowired
    private FocusSessionRepository focusRepo;

    @GetMapping("/streak/{userId}")
    public int getStreak(@PathVariable int userId) {

        List<FocusSession> sessions =
                focusRepo.findByUserIdOrderByStartTimeAsc(userId);

        if (sessions.isEmpty()) return 0;

        Set<LocalDate> uniqueDays = new HashSet<>();

        for (FocusSession s : sessions) {
            uniqueDays.add(s.getStartTime().toLocalDate());
        }

        int streak = 0;
        LocalDate today = LocalDate.now();

        while (uniqueDays.contains(today.minusDays(streak))) {
            streak++;
        }

        return streak;
    }
}