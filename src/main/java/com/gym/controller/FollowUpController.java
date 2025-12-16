package com.gym.controller;

import com.gym.entity.FollowUp;
import com.gym.service.FollowUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followups")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") 
public class FollowUpController {

    private final FollowUpService service;

    public FollowUpController(FollowUpService service) {
        this.service = service;
    }

    // GET /api/followups
    @GetMapping
    public List<FollowUp> all() {
        return service.findAll();
    }

    // POST /api/followups
    @PostMapping
    public ResponseEntity<FollowUp> create(@RequestBody FollowUp payload) {
        FollowUp created = service.create(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/followups/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FollowUp> update(@PathVariable Long id, @RequestBody FollowUp payload) {
        try {
            FollowUp updated = service.update(id, payload);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/followups/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}