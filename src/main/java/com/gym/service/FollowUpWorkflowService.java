package com.gym.service;

import com.gym.entity.FollowUpWorkflow;
import com.gym.repository.FollowUpWorkflowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUpWorkflowService {

    private final FollowUpWorkflowRepository repo;

    public FollowUpWorkflowService(FollowUpWorkflowRepository repo) {
        this.repo = repo;
    }

    public List<FollowUpWorkflow> getAll() {
        return repo.findAll();
    }

    public FollowUpWorkflow getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public FollowUpWorkflow create(FollowUpWorkflow wf) {
        wf.setId(null);
        return repo.save(wf);
    }

    public FollowUpWorkflow update(Long id, FollowUpWorkflow wf) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(wf.getName());
                    existing.setDescription(wf.getDescription());
                    existing.setFrequency(wf.getFrequency());
                    existing.setTriggerTitle(wf.getTriggerTitle());
                    existing.setTriggerTag(wf.getTriggerTag());
                    existing.setTriggerId(wf.getTriggerId());
                    existing.setActionTitle(wf.getActionTitle());
                    existing.setActionId(wf.getActionId());
                    existing.setInAppMessage(wf.getInAppMessage());
                    existing.setStatus(wf.getStatus());
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    wf.setId(id);
                    return repo.save(wf);
                });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
