package com.gym.service;

import com.gym.entity.AssetTransaction;
import com.gym.repository.AssetTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssetTransactionService {

    @Autowired
    private AssetTransactionRepository repo;

    public List<AssetTransaction> getAllTransactions() {
        return repo.findAll();
    }

    public AssetTransaction getTransactionById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public AssetTransaction createTransaction(AssetTransaction txn) {
        // Generate a Transaction ID if not present
        if (txn.getTransactionId() == null) {
            long count = repo.count() + 1;
            txn.setTransactionId("TXN-" + String.format("%03d", count) + "-" + LocalDate.now().getYear());
        }
        if (txn.getStatus() == null) {
            txn.setStatus("Pending");
        }
        return repo.save(txn);
    }

    public AssetTransaction updateTransaction(Long id, AssetTransaction details) {
        Optional<AssetTransaction> optionalTxn = repo.findById(id);
        if (optionalTxn.isPresent()) {
            AssetTransaction txn = optionalTxn.get();
            
            // Update fields
            txn.setTransactionType(details.getTransactionType());
            txn.setAssetName(details.getAssetName());
            txn.setAssetId(details.getAssetId());
            txn.setDate(details.getDate());
            txn.setValue(details.getValue());
            txn.setLocation(details.getLocation());
            txn.setAssignedTo(details.getAssignedTo());
            txn.setVendor(details.getVendor());
            txn.setInvoiceNumber(details.getInvoiceNumber());
            txn.setDescription(details.getDescription());
            txn.setNotes(details.getNotes());
            txn.setRequiresApproval(details.getRequiresApproval());
            // Status usually updated separately, but can update here too
            if (details.getStatus() != null) txn.setStatus(details.getStatus());

            return repo.save(txn);
        }
        return null;
    }

    public void deleteTransaction(Long id) {
        repo.deleteById(id);
    }

    public AssetTransaction updateStatus(Long id, String status) {
        Optional<AssetTransaction> optionalTxn = repo.findById(id);
        if (optionalTxn.isPresent()) {
            AssetTransaction txn = optionalTxn.get();
            txn.setStatus(status);
            return repo.save(txn);
        }
        return null;
    }
}