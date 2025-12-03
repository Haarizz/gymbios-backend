// src/main/java/com/yourapp/finance/service/ReceiptVoucherService.java
package com.gym.service;


import org.springframework.stereotype.Service;

import com.gym.entity.ReceiptVoucher;
import com.gym.repository.ReceiptVoucherRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptVoucherService {

    private final ReceiptVoucherRepository repository;

    public ReceiptVoucherService(ReceiptVoucherRepository repository) {
        this.repository = repository;
    }

    public List<ReceiptVoucher> getAll() {
        return repository.findAll();
    }

    public List<ReceiptVoucher> getByDate(LocalDate date) {
        return repository.findByVoucherDate(date);
    }

    public ReceiptVoucher getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt voucher not found"));
    }

    public ReceiptVoucher create(ReceiptVoucher voucher) {
        // ID will be auto-generated
        return repository.save(voucher);
    }

    public ReceiptVoucher update(Long id, ReceiptVoucher updated) {
        ReceiptVoucher existing = getById(id);

        existing.setVoucherDate(updated.getVoucherDate());
        existing.setBranchId(updated.getBranchId());
        existing.setBranchName(updated.getBranchName());
        existing.setMemberId(updated.getMemberId());
        existing.setMemberName(updated.getMemberName());
        existing.setIncomeSourceId(updated.getIncomeSourceId());
        existing.setIncomeSourceName(updated.getIncomeSourceName());
        existing.setAmount(updated.getAmount());
        existing.setPaymentMode(updated.getPaymentMode());
        existing.setReference(updated.getReference());
        existing.setNotes(updated.getNotes());
        existing.setAttachmentUrl(updated.getAttachmentUrl());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
