package com.gym.service;

import com.gym.entity.Purchase;
import com.gym.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repo;

    public Purchase createPurchase(Purchase p) {
        return repo.save(p);
    }

    public Purchase updatePurchase(Long id, Purchase p) {
        p.setId(id);
        return repo.save(p);
    }

    public List<Purchase> getAll() {
        return repo.findAll();
    }

    public Purchase getOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
