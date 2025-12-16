package com.gym.controller;

import com.gym.entity.Asset;
import com.gym.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> get(@PathVariable String id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody Asset asset) {
        if (asset.getId() == null || asset.getId().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Asset created = service.create(asset);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> update(@PathVariable String id, @RequestBody Asset asset) {
        Asset updated = service.update(id, asset);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/dispose")
    public ResponseEntity<Asset> dispose(@PathVariable String id) {
        return service.markDisposed(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/revert")
    public ResponseEntity<Asset> revert(@PathVariable String id) {
        return service.revertDisposed(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
