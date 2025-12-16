package com.gym.controller;

import com.gym.entity.Lead;
import com.gym.service.LeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lead> getLead(@PathVariable Long id) {
        return ResponseEntity.ok(leadService.getLeadById(id));
    }

    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        Lead saved = leadService.createLead(lead);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lead> updateLead(@PathVariable Long id, @RequestBody Lead lead) {
        Lead updated = leadService.updateLead(id, lead);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- Stats like in design ----------

    @GetMapping("/stats")
    public LeadStats getStats() {
        List<Lead> all = leadService.getAllLeads();
        long total = all.size();

        long converted = all.stream()
                .filter(l -> l.getStatus() != null &&
                        l.getStatus().equalsIgnoreCase("converted"))
                .count();

        long hotLeads = all.stream()
                .filter(l -> l.getPriority() != null &&
                        l.getPriority().equalsIgnoreCase("high"))
                .count();

        LocalDate today = LocalDate.now();
        long followUpsDue = all.stream()
                .filter(l -> l.getNextFollowUpDate() != null &&
                        !l.getNextFollowUpDate().isAfter(today) &&
                        (l.getStatus() == null ||
                                !l.getStatus().equalsIgnoreCase("converted")))
                .count();

        DoubleSummaryStatistics scoreStats = all.stream()
                .map(Lead::getScore)
                .filter(s -> s != null)
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();

        double avgScore = scoreStats.getCount() == 0 ? 0.0 : scoreStats.getAverage();
        double conversionRate = total == 0 ? 0.0 : (converted * 100.0 / total);

        LeadStats stats = new LeadStats();
        stats.setTotalLeads(total);
        stats.setConvertedLeads(converted);
        stats.setConversionRate(conversionRate);
        stats.setFollowUpsDue(followUpsDue);
        stats.setHotLeads(hotLeads);
        stats.setAvgScore(avgScore);

        return stats;
    }

    // simple inner class instead of DTO package
    public static class LeadStats {
        private long totalLeads;
        private long convertedLeads;
        private double conversionRate;
        private long followUpsDue;
        private long hotLeads;
        private double avgScore;

        public long getTotalLeads() {
            return totalLeads;
        }

        public void setTotalLeads(long totalLeads) {
            this.totalLeads = totalLeads;
        }

        public long getConvertedLeads() {
            return convertedLeads;
        }

        public void setConvertedLeads(long convertedLeads) {
            this.convertedLeads = convertedLeads;
        }

        public double getConversionRate() {
            return conversionRate;
        }

        public void setConversionRate(double conversionRate) {
            this.conversionRate = conversionRate;
        }

        public long getFollowUpsDue() {
            return followUpsDue;
        }

        public void setFollowUpsDue(long followUpsDue) {
            this.followUpsDue = followUpsDue;
        }

        public long getHotLeads() {
            return hotLeads;
        }

        public void setHotLeads(long hotLeads) {
            this.hotLeads = hotLeads;
        }

        public double getAvgScore() {
            return avgScore;
        }

        public void setAvgScore(double avgScore) {
            this.avgScore = avgScore;
        }
    }
}
