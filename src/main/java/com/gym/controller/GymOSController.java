package com.gym.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.entity.ActivityEntity;
import com.gym.entity.ApiIntegrationEntity;
import com.gym.entity.DeviceEntity;
import com.gym.entity.GymOSConfig;
import com.gym.entity.ModuleEntity;
import com.gym.entity.NotificationEntity;
import com.gym.service.GymOSService;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/gymos")
public class GymOSController {

    private final GymOSService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public GymOSController(GymOSService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public Map<String, Object> overview() {
        Map<String, Object> map = new HashMap<>();
        map.put("activeSections", 2);
        map.put("totalItems", 44);
        map.put("totalRoles", 5);
        return map;
    }

    @GetMapping("/modules")
    public List<Map<String, Object>> modules() {
        List<Map<String, Object>> out = new ArrayList<>();
        for (ModuleEntity m : service.getModules()) {
            out.add(Map.of(
                    "id", m.getId(),
                    "name", m.getName(),
                    "status", m.getStatus(),
                    "uptime", m.getUptime(),
                    "lastUpdate", m.getLastUpdate()
            ));
        }
        return out;
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        List<ModuleEntity> modules = service.getModules();
        List<DeviceEntity> devices = service.getDevices();
        List<ApiIntegrationEntity> apis = service.getApiIntegrations();

        Map<String, Object> map = new HashMap<>();
        map.put("modulesInstalled", modules.size());
        map.put("modulesActive", modules.stream().filter(m -> "Active".equalsIgnoreCase(m.getStatus())).count());
        map.put("usersWithPermissions", 45);
        map.put("apiIntegrations", apis.size());
        map.put("devicesOnline", devices.stream().filter(d -> "Online".equalsIgnoreCase(d.getStatus())).count());
        return map;
    }

    @GetMapping("/devices")
    public List<DeviceEntity> devices() {
        return service.getDevices();
    }

    @GetMapping("/api-integrations")
    public List<ApiIntegrationEntity> apiIntegrations() {
        return service.getApiIntegrations();
    }

    @GetMapping("/notifications")
    public List<NotificationEntity> notifications() {
        return service.getNotifications();
    }

    @GetMapping("/activity")
    public List<ActivityEntity> activity() {
        return service.getActivity();
    }

    @GetMapping("/config")
    public Map<String, Object> getConfig() {
        GymOSConfig config = service.getLatestConfig();

        Map<String, Object> response = new HashMap<>();
        response.put("payload", config != null ? config.getPayloadJson() : "");
        response.put("updatedAt", config != null ? config.getUpdatedAt() : null);

        return response;
    }


    @PostMapping("/config")
    public Map<String, String> saveConfig(@RequestBody Map<String, Object> payload) {
        try {
            String json = mapper.writeValueAsString(payload);
            service.saveConfig(json);
            return Map.of("status", "ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
