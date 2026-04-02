package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LeaderboardController {

    @Autowired
    private FocusSessionRepository focusRepo;

    @GetMapping("/leaderboard")
    public List<Map<String, Object>> getLeaderboard() {

        List<FocusSession> sessions = focusRepo.findAll();

        Map<Integer, Long> userScores = new HashMap<>();

        for (FocusSession s : sessions) {

            int userId = s.getUserId();

            long score = s.getDuration() - (s.getDistractions() * 2);

            userScores.put(userId,
                    userScores.getOrDefault(userId, 0L) + score);
        }

        List<Map<String, Object>> leaderboard = new ArrayList<>();

        for (Map.Entry<Integer, Long> entry : userScores.entrySet()) {

            Map<String, Object> user = new HashMap<>();
            user.put("userId", entry.getKey());
            user.put("score", entry.getValue());

            leaderboard.add(user);
        }

        // 🔥 SORT DESCENDING
        leaderboard.sort((a, b) ->
                Long.compare((Long) b.get("score"), (Long) a.get("score"))
        );

        return leaderboard;
    }
}