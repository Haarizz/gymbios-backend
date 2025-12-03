package com.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.entity.Account;
import com.gym.entity.CostCenter;
import com.gym.entity.LedgerTransaction;
import com.gym.repository.AccountRepository;
import com.gym.repository.CostCenterRepository;
import com.gym.repository.TransactionRepository;

@Service
public class LedgerService {

    private final AccountRepository accountRepo;
    private final CostCenterRepository costRepo;
    private final TransactionRepository txnRepo;

    public LedgerService(AccountRepository accountRepo,
                         CostCenterRepository costRepo,
                         TransactionRepository txnRepo) {
        this.accountRepo = accountRepo;
        this.costRepo = costRepo;
        this.txnRepo = txnRepo;
    }

    public List<Account> getAccounts() {
        return accountRepo.findAll();
    }

    // ✅ Auto-generate accountCode if not provided
    public Account createAccount(Account a) {
        if (a.getAccountCode() == null || a.getAccountCode().isBlank()) {
            long nextNumber = accountRepo.count() + 1;   // simple incremental
            String code = String.format("AC-%04d", nextNumber); // e.g. AC-0001
            a.setAccountCode(code);
        }
        return accountRepo.save(a);
    }

    public List<CostCenter> getCostCenters() {
        return costRepo.findAll();
    }

    public CostCenter createCostCenter(CostCenter c) {
        return costRepo.save(c);
    }

    public List<LedgerTransaction> getTransactions() {
        return txnRepo.findAll();
    }
}
