package com.gym.service;

import com.gym.entity.*;
import com.gym.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PosService {

    @Autowired private PosProductRepository productRepo;
    @Autowired private PosSessionRepository sessionRepo;
    @Autowired private PosSaleRepository saleRepo;
    @Autowired private PosHeldOrderRepository heldRepo;
    @Autowired private PosCashMovementRepository cashRepo;
    @Autowired private PosCustomerRepository customerRepo;

    public List<PosProduct> getProducts(String category) {
        if (category != null && !category.isEmpty() && !category.equals("all")) {
            return productRepo.findByCategory(category);
        }
        return productRepo.findAll();
    }

    public List<PosCustomer> getCustomers() {
        return customerRepo.findAll();
    }

    public PosSession startSession(Double openingCash) {
        PosSession session = new PosSession();
        session.setOpeningCash(openingCash);
        session.setStartTime(LocalDateTime.now());
        session.setStatus("ACTIVE");
        return sessionRepo.save(session);
    }

    public PosSession closeSession(Long id, Double closingCash) {
        PosSession session = sessionRepo.findById(id).orElseThrow(() -> new RuntimeException("Session not found"));
        session.setClosingCash(closingCash);
        session.setEndTime(LocalDateTime.now());
        session.setStatus("CLOSED");
        return sessionRepo.save(session);
    }

    @Transactional
    public PosSale createSale(PosSale sale) {
        sale.setSaleTime(LocalDateTime.now());
        // Deduct Stock
        if(sale.getItems() != null) {
            for (PosSaleItem item : sale.getItems()) {
                PosProduct p = productRepo.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
                p.setStock(p.getStock() - item.getQty());
                productRepo.save(p);
            }
        }
        return saleRepo.save(sale);
    }

    public PosHeldOrder holdOrder(PosHeldOrder order) {
        order.setTime(LocalDateTime.now());
        return heldRepo.save(order);
    }

    public List<PosHeldOrder> getHeldOrders() {
        return heldRepo.findAll();
    }

    public void deleteHeldOrder(Long id) {
        heldRepo.deleteById(id);
    }

    public PosCashMovement recordCashMovement(PosCashMovement movement) {
        movement.setTime(LocalDateTime.now());
        return cashRepo.save(movement);
    }
}