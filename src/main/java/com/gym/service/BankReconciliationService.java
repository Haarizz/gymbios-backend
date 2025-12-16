package com.gym.service;

import com.gym.entity.BankReconciliation;
import com.gym.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class BankReconciliationService {

    private final ReceiptVoucherRepository receiptRepo;
    private final PaymentVoucherRepository paymentRepo;
    private final TransactionRepository ledgerRepo;
    private final BankReconciliationRepository bankRepo;

    public BankReconciliationService(
            ReceiptVoucherRepository receiptRepo,
            PaymentVoucherRepository paymentRepo,
            TransactionRepository ledgerRepo,
            BankReconciliationRepository bankRepo) {

        this.receiptRepo = receiptRepo;
        this.paymentRepo = paymentRepo;
        this.ledgerRepo = ledgerRepo;
        this.bankRepo = bankRepo;
    }

    // ✅ FINALIZE & SAVE TO DATABASE
    @Transactional
    public BankReconciliation finalizeReconciliation(LocalDate from, LocalDate to) {

        // ✅ Prevent duplicate finalization
        bankRepo.findByFromDateAndToDate(from, to).ifPresent(r -> {
            throw new RuntimeException("Reconciliation already finalized for this period");
        });

        Double receipts = receiptRepo.sumBankReceipts(from, to);
        Double payments = paymentRepo.sumBankPayments(from, to);
        Double ledgerBalance = ledgerRepo.sumLedgerBalance(from, to);

        receipts = receipts != null ? receipts : 0.0;
        payments = payments != null ? payments : 0.0;
        ledgerBalance = ledgerBalance != null ? ledgerBalance : 0.0;

        Double opening = 0.0; // ✅ later connect Account opening balance
        Double bankBalance = opening + receipts - payments;
        Double difference = bankBalance - ledgerBalance;
        Double closing = bankBalance;

        BankReconciliation rec = new BankReconciliation();
        rec.setFromDate(from);
        rec.setToDate(to);
        rec.setOpeningBalance(opening);
        rec.setTotalReceipts(receipts);
        rec.setTotalPayments(payments);
        rec.setLedgerBalance(ledgerBalance);
        rec.setBankBalance(bankBalance);
        rec.setDifference(difference);
        rec.setClosingBalance(closing);
        rec.setRemarks(difference == 0 ? "Balanced" : "Mismatch");

        return bankRepo.save(rec);
    }

    // ✅ SUMMARY API (USED BY YOUR DASHBOARD)
    public BankReconciliation getSummary(LocalDate from, LocalDate to) {

        Double receipts = receiptRepo.sumBankReceipts(from, to);
        Double payments = paymentRepo.sumBankPayments(from, to);
        Double ledgerBalance = ledgerRepo.sumLedgerBalance(from, to);

        receipts = receipts != null ? receipts : 0.0;
        payments = payments != null ? payments : 0.0;
        ledgerBalance = ledgerBalance != null ? ledgerBalance : 0.0;

        Double opening = 0.0;
        Double bankBalance = opening + receipts - payments;
        Double difference = bankBalance - ledgerBalance;
        Double closing = bankBalance;

        BankReconciliation dto = new BankReconciliation();
        dto.setFromDate(from);
        dto.setToDate(to);
        dto.setOpeningBalance(opening);
        dto.setTotalReceipts(receipts);
        dto.setTotalPayments(payments);
        dto.setLedgerBalance(ledgerBalance);
        dto.setBankBalance(bankBalance);
        dto.setDifference(difference);
        dto.setClosingBalance(closing);

        return dto;
    }
}
