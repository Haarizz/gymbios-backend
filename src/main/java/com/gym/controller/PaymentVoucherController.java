// com/gym/controller/PaymentVoucherController.java
package com.gym.controller;

import com.gym.entity.PaymentVoucher;
import com.gym.entity.VoucherTransaction;
import com.gym.service.PaymentVoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment-vouchers")
public class PaymentVoucherController {

    private final PaymentVoucherService service;

    public PaymentVoucherController(PaymentVoucherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listAll() {
        List<PaymentVoucher> vouchers = service.findAll();
        List<Map<String, Object>> out = vouchers.stream().map(v -> {
            Map<String,Object> m = new HashMap<>();
            m.put("id", v.getId());
            m.put("voucherNo", v.getVoucherNo());
            m.put("party", v.getParty());
            m.put("type", v.getType());
            m.put("billNo", v.getBillNo());
            m.put("paymentDate", v.getPaymentDate() == null ? null : v.getPaymentDate().toString());
            m.put("amount", v.getAmount());
            m.put("method", v.getMethod());
            m.put("status", v.getStatus());
            m.put("paidAmount", v.getPaidAmount());
            // Add payments/transactions for display in table/drawer
            if (v.getPayments() != null) {
                m.put("transactions", v.getPayments().stream().map(txn -> {
                    Map<String, Object> t = new HashMap<>();
                    t.put("id", txn.getId());
                    t.put("amount", txn.getAmount());
                    t.put("method", txn.getMethod());
                    t.put("date", txn.getDate() == null ? null : txn.getDate().toString());
                    t.put("note", txn.getNote());
                    return t;
                }).collect(Collectors.toList()));
            } else {
                 m.put("transactions", Collections.emptyList());
            }
            m.put("description", v.getDescription());
            m.put("narration", v.getNarration());
            m.put("createdBy", v.getCreatedBy());
            m.put("bankAccount", v.getBankAccount());
            return m;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(out);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getOne(@PathVariable Long id) {
        return service.findById(id).map(v -> {
            Map<String,Object> m = new HashMap<>();
            m.put("id", v.getId());
            m.put("voucherNo", v.getVoucherNo());
            m.put("party", v.getParty());
            m.put("type", v.getType());
            m.put("billNo", v.getBillNo());
            m.put("paymentDate", v.getPaymentDate() == null ? null : v.getPaymentDate().toString());
            m.put("amount", v.getAmount());
            m.put("method", v.getMethod());
            m.put("status", v.getStatus());
            m.put("paidAmount", v.getPaidAmount());
            m.put("description", v.getDescription());
            m.put("narration", v.getNarration());
            m.put("createdBy", v.getCreatedBy());
            m.put("bankAccount", v.getBankAccount());
            if (v.getPayments() != null) {
                m.put("transactions", v.getPayments().stream().map(txn -> {
                    Map<String, Object> t = new HashMap<>();
                    t.put("id", txn.getId());
                    t.put("amount", txn.getAmount());
                    t.put("method", txn.getMethod());
                    t.put("date", txn.getDate() == null ? null : txn.getDate().toString());
                    t.put("note", txn.getNote());
                    return t;
                }).collect(Collectors.toList()));
            } else {
                 m.put("transactions", Collections.emptyList());
            }
            return ResponseEntity.ok(m);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentVoucher payload) {
        PaymentVoucher created = service.create(payload);
        return ResponseEntity.created(URI.create("/api/payment-vouchers/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentVoucher> update(@PathVariable Long id, @RequestBody PaymentVoucher payload) {
        PaymentVoucher updated = service.update(id, payload);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/payments")
    public ResponseEntity<PaymentVoucher> addPayment(@PathVariable("id") Long voucherId, @RequestBody VoucherTransaction paymentPayload) {
        PaymentVoucher updated = service.addPayment(voucherId, paymentPayload);
        return ResponseEntity.ok(updated);
    }
}