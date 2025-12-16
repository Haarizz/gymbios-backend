package com.gym.service;

import org.springframework.stereotype.Service;

import com.gym.entity.ActivityEntity;
import com.gym.entity.ApiIntegrationEntity;
import com.gym.entity.DeviceEntity;
import com.gym.entity.GymOSConfig;
import com.gym.entity.ModuleEntity;
import com.gym.entity.NotificationEntity;
import com.gym.repository.ActivityRepository;
import com.gym.repository.ApiIntegrationRepository;
import com.gym.repository.DeviceRepository;
import com.gym.repository.GymOSConfigRepository;
import com.gym.repository.ModuleRepository;
import com.gym.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GymOSService {

    private final ModuleRepository moduleRepo;
    private final DeviceRepository deviceRepo;
    private final ApiIntegrationRepository apiRepo;
    private final NotificationRepository notificationRepo;
    private final ActivityRepository activityRepo;
    private final GymOSConfigRepository configRepo;

    public GymOSService(ModuleRepository moduleRepo,
                        DeviceRepository deviceRepo,
                        ApiIntegrationRepository apiRepo,
                        NotificationRepository notificationRepo,
                        ActivityRepository activityRepo,
                        GymOSConfigRepository configRepo) {

        this.moduleRepo = moduleRepo;
        this.deviceRepo = deviceRepo;
        this.apiRepo = apiRepo;
        this.notificationRepo = notificationRepo;
        this.activityRepo = activityRepo;
        this.configRepo = configRepo;
    }

    public List<ModuleEntity> getModules() { return moduleRepo.findAll(); }
    public List<DeviceEntity> getDevices() { return deviceRepo.findAll(); }
    public List<ApiIntegrationEntity> getApiIntegrations() { return apiRepo.findAll(); }
    public List<NotificationEntity> getNotifications() { return notificationRepo.findAll(); }
    public List<ActivityEntity> getActivity() { return activityRepo.findAll(); }

    public GymOSConfig getLatestConfig() {
        return configRepo.findAll().stream().reduce((f, s) -> s).orElse(null);
    }

    public void saveConfig(String json) {
        GymOSConfig cfg = new GymOSConfig();
        cfg.setPayloadJson(json);
        cfg.setUpdatedAt(LocalDateTime.now());
        configRepo.save(cfg);
    }
}
