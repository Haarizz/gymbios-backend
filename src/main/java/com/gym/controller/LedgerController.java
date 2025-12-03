package com.gym.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.entity.Account;
import com.gym.entity.CostCenter;
import com.gym.entity.LedgerTransaction;
import com.gym.service.LedgerService;

@RestController
@RequestMapping("/ledgers")
public class LedgerController {

    private final LedgerService service;

    public LedgerController(LedgerService service) { 
        this.service = service;
    }

    @GetMapping("/accounts")
    public List<Account> accounts() {
        return service.getAccounts();
    }

    @PostMapping("/accounts")
    public Account createAcc(@RequestBody Account a) {
        return service.createAccount(a);
    }

    @GetMapping("/cost-centers")
    public List<CostCenter> costCenters() {
        return service.getCostCenters();
    }

    @PostMapping("/cost-centers")
    public CostCenter createCC(@RequestBody CostCenter c) {
        return service.createCostCenter(c);
    }

    @GetMapping("/transactions")
    public List<LedgerTransaction> txns() {
        return service.getTransactions();
    }
}
