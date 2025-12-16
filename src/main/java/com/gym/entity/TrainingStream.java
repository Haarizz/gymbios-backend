package com.gym.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "training_streams")
public class TrainingStream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String instructor;
    private String abbreviation;

    // store date as LocalDate; expect yyyy-MM-dd from frontend
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String time;
    private String duration;
    private String status; // LIVE / SCHEDULED
    private String difficulty;
    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer views = 0;
    private Integer likes = 0;
    private String completion; // e.g. "0/0"
    private Integer viewers = 0;

    private String visibility; // public/private/unlisted
    private Integer maxViewers = 100;
    private Boolean record = true;
    private String quality; // "720p" etc.

    public TrainingStream() {}

    // Getters & setters (generated)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getAbbreviation() { return abbreviation; }
    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }

    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }

    public String getCompletion() { return completion; }
    public void setCompletion(String completion) { this.completion = completion; }

    public Integer getViewers() { return viewers; }
    public void setViewers(Integer viewers) { this.viewers = viewers; }

    public String getVisibility() { return visibility; }
    public void setVisibility(String visibility) { this.visibility = visibility; }

    public Integer getMaxViewers() { return maxViewers; }
    public void setMaxViewers(Integer maxViewers) { this.maxViewers = maxViewers; }

    public Boolean getRecord() { return record; }
    public void setRecord(Boolean record) { this.record = record; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }
}
