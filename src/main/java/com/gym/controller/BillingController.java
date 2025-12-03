package com.gym.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gym.entity.Bill;
import com.gym.entity.PaymentRequest;
import com.gym.service.BillingService;

import java.util.List;

@RestController
@RequestMapping("/api/billing")

public class BillingController {

 private final BillingService billingService;

 public BillingController(BillingService billingService) {
     this.billingService = billingService;
 }

 @GetMapping("/bills")
 public List<Bill> getBills() {
     return billingService.getAllBills();
 }

 @GetMapping("/bills/{id}")
 public Bill getBill(@PathVariable Long id) {
     return billingService.getBill(id);
 }

 @PostMapping("/bills")
 public Bill createBill(@RequestBody Bill bill) {
     return billingService.createBill(bill);
 }

 @PutMapping("/bills/{id}")
 public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
     return billingService.updateBill(id, bill);
 }

 @DeleteMapping("/bills/{id}")
 public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
     billingService.deleteBill(id);
     return ResponseEntity.noContent().build();
 }

 @GetMapping("/members/{memberId}/pending-bills")
 public List<Bill> getPendingBills(@PathVariable Long memberId) {
     return billingService.getPendingBillsForMember(memberId);
 }

 @PostMapping("/bills/{id}/pay")
 public Bill payBill(
         @PathVariable Long id,
         @RequestBody PaymentRequest request
 ) {
     return billingService.applyPayment(id, request);
 }
}
