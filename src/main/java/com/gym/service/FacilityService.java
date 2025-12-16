package com.gym.service;

import com.gym.entity.Facility;
import com.gym.entity.Pricing;
import com.gym.repository.FacilityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository repo;

    public FacilityService(FacilityRepository repo) {
        this.repo = repo;
    }

    /**
     * List facilities with optional search and simple filter (all | active | inactive)
     */
    public List<Facility> listAll(String search, String filter) {
        if (search != null && !search.isBlank()) {
            return repo.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(search, search);
        }
        if ("active".equalsIgnoreCase(filter)) {
            return repo.findByActive(true);
        }
        if ("inactive".equalsIgnoreCase(filter)) {
            return repo.findByActive(false);
        }
        return repo.findAll();
    }

    public Optional<Facility> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public Facility create(Facility f) {
        if (f.getPricing() == null) {
            f.setPricing(new Pricing());
        }
        if (f.getBookings() == null) f.setBookings(0);
        if (f.getActive() == null) f.setActive(true);
        return repo.save(f);
    }

    @Transactional
    public Optional<Facility> update(Long id, Facility body) {
        return repo.findById(id).map(existing -> {
            existing.setName(body.getName());
            existing.setCode(body.getCode());
            existing.setActive(body.getActive() == null ? existing.getActive() : body.getActive());
            existing.setOccupancy(body.getOccupancy());
            existing.setBookings(body.getBookings() == null ? existing.getBookings() : body.getBookings());
            if (body.getPricing() != null) existing.setPricing(body.getPricing());
            existing.setDescription(body.getDescription());
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
