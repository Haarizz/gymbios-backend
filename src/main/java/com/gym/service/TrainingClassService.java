package com.gym.service;


import org.springframework.stereotype.Service;

import com.gym.entity.TrainingClass;
import com.gym.repository.TrainingClassRepository;

import java.util.List;

@Service
public class TrainingClassService {

    private final TrainingClassRepository repository;

    public TrainingClassService(TrainingClassRepository repository) {
        this.repository = repository;
    }

    public TrainingClass create(TrainingClass trainingClass) {
        return repository.save(trainingClass);
    }

    public List<TrainingClass> getAll() {
        return repository.findAll();
    }

    public TrainingClass getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
