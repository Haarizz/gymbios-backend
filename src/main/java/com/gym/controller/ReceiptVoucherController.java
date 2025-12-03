package com.gym.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.entity.ReceiptVoucher;
import com.gym.service.ReceiptVoucherService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/receipt-vouchers")
public class ReceiptVoucherController {

    private final ReceiptVoucherService service;

    public ReceiptVoucherController(ReceiptVoucherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReceiptVoucher>> getAll(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        if (date != null) {
            return ResponseEntity.ok(service.getByDate(date));
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptVoucher> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ReceiptVoucher> create(@RequestBody ReceiptVoucher voucher) {
        return ResponseEntity.ok(service.create(voucher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiptVoucher> update(@PathVariable Long id,
                                                 @RequestBody ReceiptVoucher voucher) {
        return ResponseEntity.ok(service.update(id, voucher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
