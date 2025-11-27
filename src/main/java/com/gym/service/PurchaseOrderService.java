package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.entity.PurchaseOrder;
import com.gym.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository repo;

    public PurchaseOrder createPO(PurchaseOrder po) {

        // Auto-generate PO Number if empty
        if (po.getPoNumber() == null || po.getPoNumber().isEmpty()) {
            po.setPoNumber("PO-" + System.currentTimeMillis());
        }

        return repo.save(po);
    }

    public PurchaseOrder updatePO(Long id, PurchaseOrder po) {
        po.setId(id);
        return repo.save(po);
    }

    public List<PurchaseOrder> getAll() {
        return repo.findAll();
    }

    public PurchaseOrder getOne(Long id) {
        return repo.findById(id).orElse(null);
    }
}

