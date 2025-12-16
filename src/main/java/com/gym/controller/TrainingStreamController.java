package com.gym.controller;

import com.gym.entity.TrainingStream;
import com.gym.service.TrainingStreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/streams")
public class TrainingStreamController {

    private final TrainingStreamService trainingStreamService;

    public TrainingStreamController(TrainingStreamService trainingStreamService) {
        this.trainingStreamService = trainingStreamService;
    }

    /**
     * List streams. Optional query params:
     * - search (search by title or instructor)
     * - status (e.g., LIVE or SCHEDULED)
     */
    @GetMapping
    public ResponseEntity<List<TrainingStream>> listAll(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "status", required = false) String status
    ) {
        List<TrainingStream> list = trainingStreamService.listAll(search, status);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingStream> getById(@PathVariable Long id) {
        return trainingStreamService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrainingStream> create(@RequestBody TrainingStream body) {
        if (body == null) return ResponseEntity.badRequest().build();
        TrainingStream created = trainingStreamService.create(body);
        return ResponseEntity.created(URI.create("/api/streams/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingStream> update(@PathVariable Long id, @RequestBody TrainingStream body) {
        return trainingStreamService.update(id, body)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean ok = trainingStreamService.delete(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
