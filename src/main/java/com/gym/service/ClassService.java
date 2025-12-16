package com.gym.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.gym.entity.ClassEntity;
import com.gym.repository.ClassRepo;

@Service
public class ClassService {

    private final ClassRepo repo;

    public ClassService(ClassRepo repo) {
        this.repo = repo;
    }

    public List<ClassEntity> list() {
        return repo.findAll();
    }

    public ClassEntity create(ClassEntity c) {
        return repo.save(c);
    }

    public ClassEntity update(Integer id, ClassEntity newc) {
        return repo.save(newc);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
