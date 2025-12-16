package com.gym.service;

import com.gym.entity.PaymentVoucher;
import com.gym.entity.VoucherTransaction;
import com.gym.repository.PaymentVoucherRepository;
import com.gym.repository.VoucherTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentVoucherService {

    private final PaymentVoucherRepository voucherRepository;
    private final VoucherTransactionRepository txnRepository;

    public PaymentVoucherService(PaymentVoucherRepository voucherRepository, VoucherTransactionRepository txnRepository) {
        this.voucherRepository = voucherRepository;
        this.txnRepository = txnRepository;
    }

    public List<PaymentVoucher> findAll() {
        return voucherRepository.findAll();
    }

    public Optional<PaymentVoucher> findById(Long id) {
        return voucherRepository.findById(id);
    }

    @Transactional
    public PaymentVoucher create(PaymentVoucher voucher) {
        // ensure payments' voucher reference is set if any
        if (voucher.getPayments() != null) {
            voucher.getPayments().forEach(txn -> txn.setVoucher(voucher));
        }
        // default createdBy/bankAccount if needed
        if (voucher.getPaidAmount() == null) voucher.setPaidAmount(0.0);
        return voucherRepository.save(voucher);
    }

    @Transactional
    public PaymentVoucher update(Long id, PaymentVoucher incoming) {
        PaymentVoucher existing = voucherRepository.findById(id).orElseThrow(() -> new RuntimeException("Voucher not found"));
        // merge allowed fields
        existing.setParty(incoming.getParty());
        existing.setBillNo(incoming.getBillNo());
        existing.setPaymentDate(incoming.getPaymentDate());
        existing.setAmount(incoming.getAmount());
        existing.setMethod(incoming.getMethod());
        existing.setStatus(incoming.getStatus());
        existing.setNarration(incoming.getNarration());
        existing.setDescription(incoming.getDescription());
        existing.setBankAccount(incoming.getBankAccount());
        existing.setCreatedBy(incoming.getCreatedBy());
        // do not blindly replace payments here — manage via addPayment API or clear+set carefully
        return voucherRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        voucherRepository.deleteById(id);
    }

    @Transactional
    public PaymentVoucher addPayment(Long voucherId, VoucherTransaction txn) {
        PaymentVoucher voucher = voucherRepository.findById(voucherId).orElseThrow(() -> new RuntimeException("Voucher not found"));
        txn.setVoucher(voucher);
        VoucherTransaction saved = txnRepository.save(txn);
        // add to voucher and update paid amount & status
        voucher.addPayment(saved);
        return voucherRepository.save(voucher);
    }
}