package com.gym.service;

import com.gym.entity.TaxConfiguration;
import com.gym.entity.TaxFiling;
import com.gym.repository.TaxConfigurationRepository;
import com.gym.repository.TaxFilingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaxService {

    @Autowired
    private TaxConfigurationRepository configRepo;

    @Autowired
    private TaxFilingRepository filingRepo;

    public List<TaxConfiguration> getAllConfigs() {
        return configRepo.findAll();
    }

    @Transactional
    public TaxConfiguration createConfig(TaxConfiguration config) {
        // 1. Save the Configuration
        TaxConfiguration savedConfig = configRepo.save(config);

        // 2. Automatically generate the "Pending" filing for the current period
        createInitialFiling(savedConfig);

        return savedConfig;
    }

    public TaxConfiguration updateConfig(Long id, TaxConfiguration details) {
        TaxConfiguration config = configRepo.findById(id).orElseThrow(() -> new RuntimeException("Config not found"));
        config.setType(details.getType());
        config.setFrequency(details.getFrequency());
        config.setRate(details.getRate());
        config.setAccounts(details.getAccounts());
        config.setStatus(details.getStatus());
        
        // Also update the type name in filings if it changed
        List<TaxFiling> filings = filingRepo.findByConfigId(id);
        for(TaxFiling f : filings) {
            f.setType(details.getType());
            filingRepo.save(f);
        }
        
        return configRepo.save(config);
    }

    @Transactional
    public void deleteConfig(Long id) {
        filingRepo.deleteByConfigId(id); // Delete associated filings first
        configRepo.deleteById(id);
    }

    // --- Filing Logic ---

    public List<TaxFiling> getAllFilings() {
        return filingRepo.findAll();
    }

    public TaxFiling updateFiling(Long id, TaxFiling details) {
        TaxFiling filing = filingRepo.findById(id).orElseThrow(() -> new RuntimeException("Filing not found"));
        
        filing.setAmount(details.getAmount());
        filing.setStatus(details.getStatus());
        filing.setNotes(details.getNotes());
        
        // If status is 'Filed', set filedDate to today if it's not already set
        if ("Filed".equalsIgnoreCase(details.getStatus()) && filing.getFiledDate() == null) {
            filing.setFiledDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        }
        
        return filingRepo.save(filing);
    }

    // --- Helper to Generate Automatic Filing ---
    private void createInitialFiling(TaxConfiguration config) {
        TaxFiling filing = new TaxFiling();
        filing.setConfigId(config.getId());
        filing.setType(config.getType());
        filing.setAmount(0.0);
        filing.setStatus("Pending");
        filing.setDocuments(0);
        filing.setNotes("");

        LocalDate now = LocalDate.now();
        int year = now.getYear();

        // Calculate Period and Due Date based on Frequency
        if ("Monthly".equalsIgnoreCase(config.getFrequency())) {
            String monthName = now.getMonth().toString(); // e.g., DECEMBER
            monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase(); // December
            filing.setPeriod(monthName + " " + year);
            filing.setDueDate("28 " + monthName.substring(0, 3) + " " + year);
        } 
        else if ("Quarterly".equalsIgnoreCase(config.getFrequency())) {
            int quarter = (now.getMonthValue() - 1) / 3 + 1;
            filing.setPeriod("Q" + quarter + " " + year);
            // Example: Q4 due date 31 Dec
            filing.setDueDate("31 Dec " + year);
        } 
        else {
            filing.setPeriod("Annual " + year);
            filing.setDueDate("31 Dec " + year);
        }

        filingRepo.save(filing);
    }
}