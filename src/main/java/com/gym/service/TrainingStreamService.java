package com.gym.service;

import com.gym.entity.TrainingStream;
import com.gym.repository.TrainingStreamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingStreamService {

    private final TrainingStreamRepository repo;

    public TrainingStreamService(TrainingStreamRepository repo) {
        this.repo = repo;
    }

    /**
     * List streams, optional search (by title/instructor) and optional status filter.
     * status expected like "LIVE" or "SCHEDULED" (case-insensitive)
     */
    public List<TrainingStream> listAll(String search, String status) {
        if (search != null && !search.isBlank()) {
            return repo.findByTitleContainingIgnoreCaseOrInstructorContainingIgnoreCase(search, search);
        }
        if (status != null && !status.isBlank()) {
            return repo.findByStatusIgnoreCase(status);
        }
        return repo.findAll();
    }

    public Optional<TrainingStream> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public TrainingStream create(TrainingStream s) {
        if (s.getViews() == null) s.setViews(0);
        if (s.getLikes() == null) s.setLikes(0);
        if (s.getViewers() == null) s.setViewers(0);
        if (s.getMaxViewers() == null) s.setMaxViewers(100);
        if (s.getRecord() == null) s.setRecord(true);
        // default status SCHEDULED if missing
        if (s.getStatus() == null || s.getStatus().isBlank()) s.setStatus("SCHEDULED");
        return repo.save(s);
    }

    @Transactional
    public Optional<TrainingStream> update(Long id, TrainingStream body) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(body.getTitle());
            existing.setInstructor(body.getInstructor());
            existing.setAbbreviation(body.getAbbreviation());
            existing.setDate(body.getDate());
            existing.setTime(body.getTime());
            existing.setDuration(body.getDuration());
            existing.setStatus(body.getStatus());
            existing.setDifficulty(body.getDifficulty());
            existing.setDescription(body.getDescription());
            existing.setViews(body.getViews());
            existing.setLikes(body.getLikes());
            existing.setCompletion(body.getCompletion());
            existing.setViewers(body.getViewers());
            existing.setVisibility(body.getVisibility());
            existing.setMaxViewers(body.getMaxViewers());
            existing.setRecord(body.getRecord());
            existing.setQuality(body.getQuality());
            return repo.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
