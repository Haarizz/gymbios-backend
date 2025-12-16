package com.gym.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gym.entity.Automation;
import com.gym.entity.AutomationRun;
import com.gym.entity.AutomationTemplate;
import com.gym.repository.AutomationRepository;
import com.gym.repository.AutomationRunRepository;
import com.gym.repository.AutomationTemplateRepository;


@Service
public class AutomationsService {
    private final AutomationRepository automationRepository;
    private final AutomationTemplateRepository templateRepository;
    private final AutomationRunRepository runRepository;
    	
    public AutomationsService(AutomationRepository automationRepository,AutomationTemplateRepository templateRepository,AutomationRunRepository runRepository) {
    	this.automationRepository=automationRepository;
    	this.templateRepository=templateRepository;
    	this.runRepository=runRepository;
    }
    public List<Automation> listAll(String q) {
        if (q == null || q.isBlank()) {
            return automationRepository.findAll();
        }
        return automationRepository.findAll().stream()
                .filter(a -> a.getName() != null && a.getName().toLowerCase().contains(q.toLowerCase()))
                .toList();
    }

    public Automation get(Long id) {
        return automationRepository.findById(id).orElseThrow();
    }

    public Automation create(Automation a) {
        if (a.getTotalRuns() == null) a.setTotalRuns(0);
        if (a.getMembersEngaged() == null) a.setMembersEngaged(0);
        a.setActive(Boolean.TRUE.equals(a.getActive()));
        return automationRepository.save(a);
    }

    public Automation update(Long id, Automation payload) {
        Automation db = get(id);
        db.setName(payload.getName());
        db.setDescription(payload.getDescription());
        db.setTrigger(payload.getTrigger());
        db.setActions(payload.getActions());
        db.setActive(payload.getActive());
        db.setSubject(payload.getSubject());
        db.setContent(payload.getContent());
        db.setFrequency(payload.getFrequency());
        return automationRepository.save(db);
    }

    public AutomationRun run(Long id) {
        Automation a = get(id);
        AutomationRun run = new AutomationRun();
        run.setAutomationId(id);
        run.setName(a.getName());
        run.setExecutedAt(LocalDateTime.now());
        run.setMembersTargeted(100);
        run.setMembersConverted((int) (Math.random() * 50));
        run.setConversionRate(run.getMembersConverted() * 100.0 / Math.max(1, run.getMembersTargeted()));
        run.setSuccessRate(95.0);
        runRepository.save(run);

        a.setTotalRuns((a.getTotalRuns()==null?0:a.getTotalRuns()) + 1);
        a.setLastRun(LocalDateTime.now());
        a.setMembersEngaged((a.getMembersEngaged()==null?0:a.getMembersEngaged()) + run.getMembersConverted());
        a.setConversionRate(run.getConversionRate());
        automationRepository.save(a);

        return run;
    }

    public List<AutomationTemplate> templates() {
        return templateRepository.findAll();
    }

    public List<AutomationRun> recentRuns() {
        return runRepository.findTop10ByOrderByExecutedAtDesc();
    }

    public Map<String, Object> analytics() {
        List<Automation> allAutomations = automationRepository.findAll();
        long totalRunsCount = runRepository.count();

        Map<String, Object> m = new HashMap<>();

        long active = allAutomations.stream()
                .filter(a -> Boolean.TRUE.equals(a.getActive()))
                .count();
        m.put("active", active);

        long membersEngaged = allAutomations.stream()
                .mapToInt(a -> a.getMembersEngaged() == null ? 0 : a.getMembersEngaged())
                .sum();
        m.put("membersEngaged", membersEngaged);

        m.put("totalRuns", totalRunsCount);

        m.put("successRate", 98.2); 
        m.put("openRate", 92.3); 

        return m;
    }
}