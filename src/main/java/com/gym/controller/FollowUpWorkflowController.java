package com.gym.controller;

import com.gym.entity.FollowUpWorkflow;
import com.gym.service.FollowUpWorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followup-workflows")
@CrossOrigin(origins = "http://localhost:5173")
public class FollowUpWorkflowController {

    private final FollowUpWorkflowService service;

    public FollowUpWorkflowController(FollowUpWorkflowService service) {
        this.service = service;
    }

    @GetMapping
    public List<FollowUpWorkflow> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FollowUpWorkflow getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public FollowUpWorkflow create(@RequestBody FollowUpWorkflow wf) {
        return service.create(wf);
    }

    @PutMapping("/{id}")
    public FollowUpWorkflow update(@PathVariable Long id,
                                   @RequestBody FollowUpWorkflow wf) {
        return service.update(id, wf);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
