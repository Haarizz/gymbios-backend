package com.gym.controller;

import com.gym.entity.Journal;
import com.gym.repository.JournalRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/journals")
public class JournalApiController {

    private final JournalRepository repo;

    public JournalApiController(JournalRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody Journal incoming, Authentication authentication) {
        String user = authentication != null ? authentication.getName() : "system";

        if (incoming.getPreparedBy() == null || incoming.getPreparedBy().isBlank()) {
            incoming.setPreparedBy(user);
        }
        if (incoming.getJournalDate() == null) {
            incoming.setJournalDate(LocalDate.now());
        }
        incoming.setLinesAndAttach(incoming.getLines());
        incoming.recalcTotals();

        long nextSeq = repo.findTopByOrderByIdDesc().map(j -> j.getId() + 1).orElse(1L);
        incoming.setVoucherNo("JV-" + (1000 + nextSeq));

        Journal saved = repo.save(incoming);
        return ResponseEntity.created(URI.create("/api/journals/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Journal incoming, Authentication authentication) {
        Optional<Journal> opt = repo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Journal existing = opt.get();

        if (incoming.getJournalDate() != null) existing.setJournalDate(incoming.getJournalDate());
        existing.setReference(incoming.getReference());
        existing.setNarration(incoming.getNarration());
        if (incoming.getStatus() != null) existing.setStatus(incoming.getStatus());

        String user = authentication != null ? authentication.getName() : "system";
        if (incoming.getPreparedBy() != null && !incoming.getPreparedBy().isBlank()) {
            existing.setPreparedBy(incoming.getPreparedBy());
        } else if (existing.getPreparedBy() == null || existing.getPreparedBy().isBlank()) {
            existing.setPreparedBy(user);
        }

        existing.setLinesAndAttach(incoming.getLines());
        existing.recalcTotals();

        Journal saved = repo.save(existing);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
