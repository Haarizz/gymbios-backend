package com.gym.service;

import com.gym.entity.FollowUp;
import com.gym.repository.FollowUpRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowUpService {

    private final FollowUpRepository repository;

    public FollowUpService(FollowUpRepository repository) {
        this.repository = repository;
    }

    public List<FollowUp> findAll() {
        // Return latest follow-ups first
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public FollowUp create(FollowUp followUp) {
        // Set default values if missing
        if (followUp.getStatus() == null || followUp.getStatus().isBlank()) {
            followUp.setStatus("Pending");
        }
        if (followUp.getPriority() == null || followUp.getPriority().isBlank()) {
            followUp.setPriority("Medium");
        }
        if (followUp.getTags() == null || followUp.getTags().isBlank()) {
            followUp.setTags("manual-entry");
        }
        if (followUp.getReason() == null || followUp.getReason().isBlank()) {
            followUp.setReason("Manual Entry");
        }
        if (followUp.getOutcome() == null || followUp.getOutcome().isBlank()) {
            followUp.setOutcome("Pending");
        }

        return repository.save(followUp);
    }

    public FollowUp update(Long id, FollowUp updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setMemberName(updatedData.getMemberName());
            existing.setMemberEmail(updatedData.getMemberEmail());
            existing.setMemberPhone(updatedData.getMemberPhone());
            existing.setSubject(updatedData.getSubject());
            existing.setType(updatedData.getType());
            existing.setPriority(updatedData.getPriority());
            existing.setStatus(updatedData.getStatus());
            existing.setDueDate(updatedData.getDueDate());
            existing.setDueTime(updatedData.getDueTime());
            existing.setAssignedStaff(updatedData.getAssignedStaff());
            existing.setNotes(updatedData.getNotes());
            existing.setTags(updatedData.getTags());
            existing.setReason(updatedData.getReason());
            existing.setOutcome(updatedData.getOutcome());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("FollowUp not found with id " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}