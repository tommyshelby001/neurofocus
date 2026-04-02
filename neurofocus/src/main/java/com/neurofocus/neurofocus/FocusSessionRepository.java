package com.neurofocus.neurofocus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FocusSessionRepository extends JpaRepository<FocusSession, Integer> {

    // 🔥 REQUIRED FOR PRODUCTIVITY SCORE
    List<FocusSession> findByUserId(int userId);
    List<FocusSession> findByUserIdOrderByStartTimeAsc(int userId);
    List<FocusSession> findAll();
}