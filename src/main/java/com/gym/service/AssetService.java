package com.gym.service;

import com.gym.entity.Asset;
import com.gym.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    private final AssetRepository repo;

    public AssetService(AssetRepository repo) {
        this.repo = repo;
    }

    public List<Asset> listAll() {
        return repo.findAll();
    }

    public Optional<Asset> findById(String id) {
        return repo.findById(id);
    }

    public Asset create(Asset asset) {
        // if currentValue not provided, set to cost
        if ((asset.getCurrentValue() == null || asset.getCurrentValue().isBlank()) && asset.getCost() != null) {
            asset.setCurrentValue(asset.getCost());
        }
        return repo.save(asset);
    }

    public Asset update(String id, Asset payload) {
        return repo.findById(id).map(existing -> {
            existing.setName(payload.getName());
            existing.setSerial(payload.getSerial());
            existing.setModel(payload.getModel());
            existing.setVendor(payload.getVendor());
            existing.setBranch(payload.getBranch());
            existing.setCategory(payload.getCategory());
            existing.setLocation(payload.getLocation());
            existing.setCost(payload.getCost());
            existing.setCurrentValue(payload.getCurrentValue());
            existing.setPurchaseDate(payload.getPurchaseDate());
            existing.setWarrantyExpiry(payload.getWarrantyExpiry());
            existing.setDepreciationRate(payload.getDepreciationRate());
            existing.setStatus(payload.getStatus());
            existing.setNotes(payload.getNotes());
            existing.setMaintenanceDate(payload.getMaintenanceDate());
            existing.setCondition(payload.getCondition());
            return repo.save(existing);
        }).orElseGet(() -> {
            // if not found, create new with provided id
            payload.setId(id);
            return repo.save(payload);
        });
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public Optional<Asset> markDisposed(String id) {
        return repo.findById(id).map(a -> {
            a.setStatus("Disposed");
            return repo.save(a);
        });
    }

    public Optional<Asset> revertDisposed(String id) {
        return repo.findById(id).map(a -> {
            a.setStatus("In Use");
            return repo.save(a);
        });
    }
}
