package com.gym.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

/**
 * Embeddable feedback details for a session.
 */
@Embeddable
public class Feedback {

    @Min(value = 1, message = "Overall must be between 1 and 5")
    @Max(value = 5, message = "Overall must be between 1 and 5")
    private Integer overall;

    @Min(value = 1, message = "Intensity must be between 1 and 5")
    @Max(value = 5, message = "Intensity must be between 1 and 5")
    private Integer intensity;

    @Size(max = 20)
    private String recommend;     // "Yes", "Maybe", "No"

    @Min(value = 1, message = "Return score must be between 1 and 10")
    @Max(value = 10, message = "Return score must be between 1 and 10")
    private Integer returnScore;  // 1-10

    @Size(max = 30)
    private String energy;        // "Low", "Medium", "High"

    @Min(value = 1, message = "Equipment rating must be between 1 and 5")
    @Max(value = 5, message = "Equipment rating must be between 1 and 5")
    private Integer equipment;

    @Min(value = 1, message = "Facility rating must be between 1 and 5")
    @Max(value = 5, message = "Facility rating must be between 1 and 5")
    private Integer facility;

    // store lists as comma-separated strings for simplicity
    @Size(max = 500)
    private String bestAspects;

    @Size(max = 500)
    private String improvements;

    @Size(max = 2000)
    private String notes;

    @Size(max = 2000)
    private String suggestions;

    public Feedback() {}

    // Getters and setters

    public Integer getOverall() {
        return overall;
    }

    public void setOverall(Integer overall) {
        this.overall = overall;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getReturnScore() {
        return returnScore;
    }

    public void setReturnScore(Integer returnScore) {
        this.returnScore = returnScore;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    public Integer getFacility() {
        return facility;
    }

    public void setFacility(Integer facility) {
        this.facility = facility;
    }

    public String getBestAspects() {
        return bestAspects;
    }

    public void setBestAspects(String bestAspects) {
        this.bestAspects = bestAspects;
    }

    public String getImprovements() {
        return improvements;
    }

    public void setImprovements(String improvements) {
        this.improvements = improvements;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}
