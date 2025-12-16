package com.gym.service;

import com.gym.entity.Sale;
import com.gym.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public Sale createSale(Sale sale) {
        return repository.save(sale);
    }

    public List<Sale> getAllSales() {
        return repository.findAll();
    }
    
    
}
