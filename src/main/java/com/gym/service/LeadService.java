package com.gym.service;

import com.gym.entity.Lead;
import com.gym.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    public Lead getLeadById(Long id) {
        return leadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lead not found"));
    }

    public Lead createLead(Lead lead) {
        applyDefaults(lead);
        return leadRepository.save(lead);
    }

    public Lead updateLead(Long id, Lead updated) {
        Lead existing = getLeadById(id);

        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setSource(updated.getSource());
        existing.setStatus(updated.getStatus());
        existing.setPriority(updated.getPriority());
        existing.setScore(updated.getScore());
        existing.setTags(updated.getTags());
        existing.setAssignedTo(updated.getAssignedTo());
        existing.setVisitDate(updated.getVisitDate());
        existing.setNextFollowUpDate(updated.getNextFollowUpDate());
        existing.setNotes(updated.getNotes());
        existing.setPhotoUrl(updated.getPhotoUrl());

        applyDefaults(existing);
        return leadRepository.save(existing);
    }

    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

    private void applyDefaults(Lead lead) {
        if (lead.getStatus() == null || lead.getStatus().isBlank()) {
            lead.setStatus("new");
        }
        if (lead.getPriority() == null || lead.getPriority().isBlank()) {
            lead.setPriority("medium");
        }
        if (lead.getScore() == null) {
            lead.setScore(0);
        }
        // visitDate / nextFollowUpDate can be null, that's fine
    }
}
