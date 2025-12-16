package com.gym.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.entity.Automation;
import com.gym.entity.AutomationRun;
import com.gym.entity.AutomationTemplate;
import com.gym.service.AutomationsService;


@RestController
@RequestMapping("/api/automations")
public class AutomationsController {
    private final AutomationsService service;

    public AutomationsController(AutomationsService service) {
		this.service = service;
	}

	@GetMapping
    public List<Automation> list(@RequestParam(required = false) String q) {
        return service.listAll(q);
    }

    @GetMapping("/{id}")
    public Automation get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public Automation create(@RequestBody Automation payload) {
        return service.create(payload);
    }

    @PutMapping("/{id}")
    public Automation update(@PathVariable Long id, @RequestBody Automation payload) {
        return service.update(id, payload);
    }

    @PostMapping("/{id}/run")
    public AutomationRun run(@PathVariable Long id) {
        return service.run(id);
    }

    @GetMapping("/templates")
    public List<AutomationTemplate> templates() {
        return service.templates();
    }

    @GetMapping("/runs/recent")
    public List<AutomationRun> recentRuns() {
        return service.recentRuns();
    }

    @GetMapping("/analytics")
    public Map<String,Object> analytics() {
        return service.analytics();
    }
}
