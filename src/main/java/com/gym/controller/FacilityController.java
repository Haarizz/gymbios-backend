package com.gym.controller;

import com.gym.entity.Facility;
import com.gym.service.FacilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    /**
     * List facilities. Optional query params:
     * - search (search name or code)
     * - filter (all | active | inactive)
     */
    @GetMapping
    public ResponseEntity<List<Facility>> listAll(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "filter", defaultValue = "all") String filter
    ) {
        List<Facility> list = facilityService.listAll(search, filter);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getById(@PathVariable Long id) {
        return facilityService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Facility> create(@RequestBody Facility body) {
        if (body == null) return ResponseEntity.badRequest().build();
        Facility created = facilityService.create(body);
        // Return 201 Created with Location header
        return ResponseEntity.created(URI.create("/api/facilities/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facility> update(@PathVariable Long id, @RequestBody Facility body) {
        return facilityService.update(id, body)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean ok = facilityService.delete(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
