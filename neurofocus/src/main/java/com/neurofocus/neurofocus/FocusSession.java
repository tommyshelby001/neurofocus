package com.neurofocus.neurofocus;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FocusSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private long duration;

    // 🔥 NEW FIELD
    private int distractions;

    // ===== GETTERS & SETTERS =====

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getDistractions() {
        return distractions;
    }

    public void setDistractions(int distractions) {
        this.distractions = distractions;
    }
}