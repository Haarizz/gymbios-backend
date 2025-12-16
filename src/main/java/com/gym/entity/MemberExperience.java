package com.gym.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member_experience")
public class MemberExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to your existing Member entity
    @Column(name = "member_id")
    private String memberId; 

    private String memberName; // Stored for easier display
    private String memberCode;
    
    private String workoutType;
    private String trainer;
    private LocalDateTime sessionTime;
    private Integer duration;
    private Integer calories;
    private Integer avgHR;
    
    // Feedback Fields
    private Integer overall;
    private Integer intensity;
    private Integer trainerRating;
    private Integer facilityRating;
    private Integer equipmentRating;
    private String recommend; // Yes, Maybe, No
    private String difficulty;
    private String pace;
    
    // Storing arrays as simple strings for simplicity (e.g., "Music,Energy")
    private String bestAspects; 
    private String improvements;
    
    @Column(length = 1000)
    private String comments;
    
    @Column(length = 1000)
    private String suggestions;
    
    private String energy;
    private Integer returnLikelihood;
    private String trainerRecommend;
    
    // Flags
    private boolean flagged;
    private boolean needsFollowUp;
    private String status; // completed, in-progress

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public String getMemberCode() { return memberCode; }
    public void setMemberCode(String memberCode) { this.memberCode = memberCode; }
    public String getWorkoutType() { return workoutType; }
    public void setWorkoutType(String workoutType) { this.workoutType = workoutType; }
    public String getTrainer() { return trainer; }
    public void setTrainer(String trainer) { this.trainer = trainer; }
    public LocalDateTime getSessionTime() { return sessionTime; }
    public void setSessionTime(LocalDateTime sessionTime) { this.sessionTime = sessionTime; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public Integer getCalories() { return calories; }
    public void setCalories(Integer calories) { this.calories = calories; }
    public Integer getAvgHR() { return avgHR; }
    public void setAvgHR(Integer avgHR) { this.avgHR = avgHR; }
    public Integer getOverall() { return overall; }
    public void setOverall(Integer overall) { this.overall = overall; }
    public Integer getIntensity() { return intensity; }
    public void setIntensity(Integer intensity) { this.intensity = intensity; }
    public Integer getTrainerRating() { return trainerRating; }
    public void setTrainerRating(Integer trainerRating) { this.trainerRating = trainerRating; }
    public Integer getFacilityRating() { return facilityRating; }
    public void setFacilityRating(Integer facilityRating) { this.facilityRating = facilityRating; }
    public Integer getEquipmentRating() { return equipmentRating; }
    public void setEquipmentRating(Integer equipmentRating) { this.equipmentRating = equipmentRating; }
    public String getRecommend() { return recommend; }
    public void setRecommend(String recommend) { this.recommend = recommend; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getPace() { return pace; }
    public void setPace(String pace) { this.pace = pace; }
    public String getBestAspects() { return bestAspects; }
    public void setBestAspects(String bestAspects) { this.bestAspects = bestAspects; }
    public String getImprovements() { return improvements; }
    public void setImprovements(String improvements) { this.improvements = improvements; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public String getSuggestions() { return suggestions; }
    public void setSuggestions(String suggestions) { this.suggestions = suggestions; }
    public String getEnergy() { return energy; }
    public void setEnergy(String energy) { this.energy = energy; }
    public Integer getReturnLikelihood() { return returnLikelihood; }
    public void setReturnLikelihood(Integer returnLikelihood) { this.returnLikelihood = returnLikelihood; }
    public String getTrainerRecommend() { return trainerRecommend; }
    public void setTrainerRecommend(String trainerRecommend) { this.trainerRecommend = trainerRecommend; }
    public boolean isFlagged() { return flagged; }
    public void setFlagged(boolean flagged) { this.flagged = flagged; }
    public boolean isNeedsFollowUp() { return needsFollowUp; }
    public void setNeedsFollowUp(boolean needsFollowUp) { this.needsFollowUp = needsFollowUp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}