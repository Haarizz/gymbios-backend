package com.gym.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.entity.Bill;
import com.gym.entity.PaymentRequest;
import com.gym.repository.BillRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillingService {

 private final BillRepository billRepository;

 public BillingService(BillRepository billRepository) {
     this.billRepository = billRepository;
 }

 public List<Bill> getAllBills() {
     return billRepository.findAll();
 }

 public Bill getBill(Long id) {
     return billRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Bill not found"));
 }

 public Bill createBill(Bill bill) {
     if (bill.getStatus() == null) {
         bill.setStatus("PENDING");
     }
     if (bill.getBillDate() == null) {
         bill.setBillDate(LocalDate.now());
     }
     if (bill.getPaidAmount() == null) {
         bill.setPaidAmount(BigDecimal.ZERO);
     }
     return billRepository.save(bill);
 }

 public Bill updateBill(Long id, Bill updated) {
     Bill existing = getBill(id);
     existing.setInvoiceNumber(updated.getInvoiceNumber());
     existing.setMemberId(updated.getMemberId());
     existing.setMemberName(updated.getMemberName());
     existing.setMemberEmail(updated.getMemberEmail());
     existing.setService(updated.getService());
     existing.setType(updated.getType());
     existing.setAmount(updated.getAmount());
     existing.setPaidAmount(updated.getPaidAmount());
     existing.setBillDate(updated.getBillDate());
     existing.setDueDate(updated.getDueDate());
     existing.setPaymentMethod(updated.getPaymentMethod());
     existing.setStatus(updated.getStatus());
     existing.setLastPaymentDate(updated.getLastPaymentDate());
     existing.setNotes(updated.getNotes());
     return billRepository.save(existing);
 }

 public void deleteBill(Long id) {
     billRepository.deleteById(id);
 }

 public List<Bill> getPendingBillsForMember(Long memberId) {
     return billRepository.findByMemberIdAndStatus(memberId, "PENDING");
 }

 @Transactional
 public Bill applyPayment(Long billId, PaymentRequest request) {
     Bill bill = getBill(billId);
     BigDecimal amount = request.getAmount();
     if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
         throw new IllegalArgumentException("Invalid payment amount");
     }

     BigDecimal currentPaid =
             bill.getPaidAmount() != null ? bill.getPaidAmount() : BigDecimal.ZERO;
     BigDecimal newPaid = currentPaid.add(amount);

     if (newPaid.compareTo(bill.getAmount()) > 0) {
         throw new IllegalArgumentException("Payment exceeds bill amount");
     }

     bill.setPaidAmount(newPaid);
     bill.setPaymentMethod(request.getPaymentMode());
     bill.setLastPaymentDate(
             request.getPaymentDate() != null ? request.getPaymentDate() : LocalDate.now()
     );

     int cmp = newPaid.compareTo(bill.getAmount());
     if (cmp == 0) {
         bill.setStatus("PAID");
     } else if (cmp > 0) {
         bill.setStatus("PAID");
     } else if (newPaid.compareTo(BigDecimal.ZERO) > 0) {
         bill.setStatus("PARTIAL");
     } else {
         bill.setStatus("PENDING");
     }

     return billRepository.save(bill);
 }
}

